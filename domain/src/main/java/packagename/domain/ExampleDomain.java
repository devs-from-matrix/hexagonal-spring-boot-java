package packagename.domain;

import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;
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
  public ExampleInfo getExamples() {
    return ExampleInfo.builder().examples(obtainExample.getAllExamples()).build();
  }

  @Override
  public Example getExampleByCode(Long code) {
    var example = obtainExample.getExampleByCode(code);
    return example.orElseThrow(() -> new ExampleNotFoundException(code));
  }

  @Override
  public Example saveExample(Example example) {
    var savedExample = obtainExample.saveExample(example);
    return savedExample.orElseThrow(() -> new ExampleNotFoundException(example.getCode()));
  }

  @Override
  public Example updateExample(Example example, Long code) {
    var exampleSaved =
        obtainExample
            .getExampleByCode(code)
            .map(
                existingExample -> {
                  existingExample.setDescription(example.getDescription());
                  return obtainExample
                      .saveExample(existingExample)
                      .orElseThrow(() -> new ExampleNotFoundException(existingExample.getCode()));
                });
    return exampleSaved.orElseThrow(() -> new ExampleNotFoundException(example.getCode()));
  }
}
