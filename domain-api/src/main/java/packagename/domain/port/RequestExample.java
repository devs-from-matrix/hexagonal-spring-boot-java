package packagename.domain.port;

import java.util.List;
import lombok.NonNull;
import packagename.domain.model.Example;

public interface RequestExample {

  List<Example> getExamples();

  Example getExampleByCode(@NonNull Long code);
}
