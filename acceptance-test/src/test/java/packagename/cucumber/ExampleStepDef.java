package packagename.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.cucumber.java8.HookNoArgsBody;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import packagename.domain.model.Example;
import packagename.repository.dao.ExampleDao;
import packagename.repository.entity.ExampleEntity;
import packagename.rest.generated.model.ExampleInfo;
import packagename.rest.generated.model.ProblemDetail;

public class ExampleStepDef implements En {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/examples";
  @LocalServerPort private int port;
  private ResponseEntity responseEntity;

  public ExampleStepDef(TestRestTemplate restTemplate, ExampleDao exampleDao) {

    DataTableType(
        (Map<String, String> row) ->
            Example.builder()
                .code(Long.parseLong(row.get("code")))
                .description(row.get("description"))
                .build());
    DataTableType(
        (Map<String, String> row) ->
            ExampleEntity.builder()
                .code(Long.parseLong(row.get("code")))
                .description(row.get("description"))
                .build());

    Before((HookNoArgsBody) exampleDao::deleteAll);
    After((HookNoArgsBody) exampleDao::deleteAll);

    Given(
        "the following examples exists in the library",
        (DataTable dataTable) -> {
          List<ExampleEntity> poems = dataTable.asList(ExampleEntity.class);
          exampleDao.saveAll(poems);
        });

    When(
        "user requests for all examples",
        () -> {
          String url = LOCALHOST + port + API_URI;
          responseEntity = restTemplate.getForEntity(url, ExampleInfo.class);
        });

    When(
        "user requests for examples by code {string}",
        (String code) -> {
          String url = LOCALHOST + port + API_URI + "/" + code;
          responseEntity = restTemplate.getForEntity(url, Example.class);
        });

    When(
        "user requests for examples by id {string} that does not exists",
        (String code) -> {
          String url = LOCALHOST + port + API_URI + "/" + code;
          responseEntity = restTemplate.getForEntity(url, ProblemDetail.class);
        });

    Then(
        "the user gets an exception {string}",
        (String exception) -> {
          assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
          var actualResponse = (ProblemDetail) responseEntity.getBody();
          var expectedProblemDetail =
              ProblemDetail.builder()
                  .type("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404")
                  .status(HttpStatus.NOT_FOUND.value())
                  .detail("Example with code 10000 does not exist")
                  .instance("/api/v1/examples/10000")
                  .title("Example not found")
                  .build();
          assertThat(actualResponse).isNotNull();
          assertThat(actualResponse)
              .usingRecursiveComparison()
              .ignoringFields("timestamp")
              .isEqualTo(expectedProblemDetail);
          assertThat(actualResponse.getTimestamp())
              .isCloseTo(LocalDateTime.now(), within(100L, ChronoUnit.SECONDS));
        });

    Then(
        "the user gets the following examples",
        (DataTable dataTable) -> {
          List<Example> expectedExamples = dataTable.asList(Example.class);
          assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
          Object body = responseEntity.getBody();
          assertThat(body).isNotNull();
          if (body instanceof ExampleInfo) {
            assertThat(((ExampleInfo) body).getExamples())
                .isNotEmpty()
                .extracting("description")
                .containsAll(
                    expectedExamples.stream()
                        .map(Example::getDescription)
                        .collect(Collectors.toList()));
          } else if (body instanceof Example) {
            assertThat(body)
                .isNotNull()
                .extracting("description")
                .isEqualTo(expectedExamples.get(0).getDescription());
          }
        });
  }
}
