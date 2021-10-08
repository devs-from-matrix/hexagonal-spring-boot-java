package packagename.domain.port;

import lombok.NonNull;
import packagename.domain.model.Example;
import packagename.domain.model.ExampleInfo;

public interface RequestExample {

  ExampleInfo getExamples();

  Example getExampleByCode(@NonNull Long code);
}
