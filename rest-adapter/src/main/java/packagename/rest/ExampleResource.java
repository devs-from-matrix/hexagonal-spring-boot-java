package packagename.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;
import packagename.domain.port.RequestExample;

@RestController
@RequestMapping("/api/v1/examples")
public class ExampleResource {

  private final RequestExample requestExample;

  public ExampleResource(RequestExample requestExample) {
    this.requestExample = requestExample;
  }

  @GetMapping
  public ResponseEntity<ExampleInfo> getExamples() {
    return ResponseEntity.ok(requestExample.getExamples());
  }

  @GetMapping("/{code}")
  public ResponseEntity<Example> getExampleByCode(@PathVariable Long code) {
    return ResponseEntity.ok(requestExample.getExampleByCode(code));
  }

  @PostMapping
  public ResponseEntity<Example> saveExample(@RequestBody Example example) {
    return ResponseEntity.ok(requestExample.saveExample(example));
  }

  @PutMapping("/{code}")
  public ResponseEntity<Example> updateExample(
      @RequestBody Example example, @PathVariable Long code) {
    return ResponseEntity.ok(requestExample.updateExample(example, code));
  }

  @DeleteMapping("/{code}")
  public ResponseEntity<Void> deleteExampleByCode(@PathVariable Long code) {
    requestExample.deleteExampleByCode(code);
    return ResponseEntity.noContent().build();
  }
}
