package packagename.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import packagename.domain.model.Example;
import packagename.domain.port.RequestExample;
import packagename.rest.generated.api.ExampleApi;
import packagename.rest.generated.model.ExampleInfo;

@RestController
public class ExampleResource implements ExampleApi {

  private final RequestExample requestExample;

  public ExampleResource(RequestExample requestExample) {
    this.requestExample = requestExample;
  }

  public ResponseEntity<ExampleInfo> getExamples() {
    return ResponseEntity.ok(ExampleInfo.builder().examples(requestExample.getExamples()).build());
  }

  public ResponseEntity<Example> getExampleByCode(@PathVariable("code") Long code) {
    return ResponseEntity.ok(requestExample.getExampleByCode(code));
  }
}
