package packagename.domain.port;

import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;

public interface RequestExample {

  ExampleInfo getExamples();

  Example getExampleByCode(Long code);
}
