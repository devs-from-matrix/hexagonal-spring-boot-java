package packagename.domain;

import java.util.Optional;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;
import packagename.domain.port.ObtainExample;
import packagename.domain.port.RequestExample;

public class ExampleDomain implements RequestExample {

  private ObtainExample obtainExample;

  public ExampleDomain() {
    this(new ObtainExample() {
    });
  }

  public ExampleDomain(ObtainExample obtainExample) {
    this.obtainExample = obtainExample;
  }

  @Override
  public ExampleInfo getExamples() {
    return ExampleInfo.builder().examples(obtainExample.getAllExamples()).build();
  }

  @Override
  public Example getExampleByCode(Long code) {
    Optional<Example> example = obtainExample.getExampleByCode(code);
    return example.orElseThrow(() -> new ExampleNotFoundException(code));
  }
}
