package packagename.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import packagename.domain.model.Example;
import packagename.domain.port.RequestExample;
import packagename.rest.generated.api.ExampleApi;
import packagename.rest.generated.model.ExampleInfo;

@RestController
@RequestMapping("/api/v1/examples")
public class ExampleResource implements ExampleApi {

  private final RequestExample requestExample;

  public ExampleResource(RequestExample requestExample) {
    this.requestExample = requestExample;
  }

  @GetMapping
  public ResponseEntity<ExampleInfo> getExamples() {
    return ResponseEntity.ok(ExampleInfo.builder().examples(requestExample.getExamples()).build());
  }

  @GetMapping("/{code}")
  public ResponseEntity<Example> getExampleByCode(@PathVariable Long code) {
    return ResponseEntity.ok(requestExample.getExampleByCode(code));
  }
}
