package packagename.domain.port;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import packagename.domain.model.Example;

public interface ObtainExample {

  default List<Example> getAllExamples() {
    Example example =
        Example.builder()
            .code(1L)
            .description(
                "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
            .build();
    return List.of(example);
  }

  default Optional<Example> getExampleByCode(@NonNull Long code) {
    return Optional.of(
        Example.builder()
            .code(1L)
            .description(
                "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
            .build());
  }
}
