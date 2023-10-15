package packagename.domain;

import java.util.List;
import lombok.NonNull;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;
import packagename.domain.port.RequestExample;

public class ExampleDomain implements RequestExample {

  private final ObtainExample obtainExample;

  public ExampleDomain() {
    this(new ObtainExample() {});
  }

  public ExampleDomain(ObtainExample obtainExample) {
    this.obtainExample = obtainExample;
  }

  @Override
  public List<Example> getExamples() {
    return obtainExample.getAllExamples();
  }

  @Override
  public Example getExampleByCode(@NonNull Long code) {
    var example = obtainExample.getExampleByCode(code);
    return example.orElseThrow(() -> new ExampleNotFoundException(code));
  }
}
