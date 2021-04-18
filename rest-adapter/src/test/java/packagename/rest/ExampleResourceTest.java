package packagename.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;
import packagename.domain.port.RequestExample;
import packagename.rest.exception.ExampleExceptionResponse;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ExampleRestAdapterApplication.class, webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ExampleResourceTest {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/examples";
  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private RequestExample requestExample;

  @Test
  @DisplayName("should start the rest adapter application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Test
  @DisplayName("should give examples when asked for examples with the support of domain stub")
  public void obtainExamplesFromDomainStub() {
    // Given
    Example example = Example.builder().code(1L).description("Johnny Johnny Yes Papa !!").build();
    Mockito.lenient()
        .when(requestExample.getExamples())
        .thenReturn(ExampleInfo.builder().examples(List.of(example)).build());
    // When
    String url = LOCALHOST + port + API_URI;
    ResponseEntity<ExampleInfo> responseEntity = restTemplate.getForEntity(url, ExampleInfo.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().getExamples())
        .isNotEmpty()
        .extracting("description")
        .contains("Johnny Johnny Yes Papa !!");
  }

  @Test
  @DisplayName(
      "should give the example when asked for an example by code with the support of domain stub")
  public void obtainExampleByCodeFromDomainStub() {
    // Given
    Long code = 1L;
    String description = "Johnny Johnny Yes Papa !!";
    Example example = Example.builder().code(code).description(description).build();
    Mockito.lenient().when(requestExample.getExampleByCode(code)).thenReturn(example);
    // When
    String url = LOCALHOST + port + API_URI + "/" + code;
    ResponseEntity<Example> responseEntity = restTemplate.getForEntity(url, Example.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody()).isEqualTo(example);
  }

  @Test
  @DisplayName(
      "should give exception when asked for an example by code that does not exists with the support of domain stub")
  public void shouldGiveExceptionWhenAskedForAnExampleByCodeFromDomainStub() {
    // Given
    Long code = -1000L;
    Mockito.lenient()
        .when(requestExample.getExampleByCode(code))
        .thenThrow(new ExampleNotFoundException(code));
    // When
    String url = LOCALHOST + port + API_URI + "/" + code;
    ResponseEntity<ExampleExceptionResponse> responseEntity =
        restTemplate.getForEntity(url, ExampleExceptionResponse.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody())
        .isEqualTo(
            ExampleExceptionResponse.builder()
                .message("Example with code " + code + " does not exist")
                .path(API_URI + "/" + code)
                .build());
  }
}
