package packagename;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import packagename.domain.ExampleDomain;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {

  @Test
  @DisplayName("should be able to get examples when asked for examples from hard coded examples")
  public void getExamplesFromHardCoded() {
    /*
       RequestExample    - left side port
       ExampleDomain     - hexagon (domain)
       ObtainExample     - right side port
    */
    var requestExample = new ExampleDomain(); // the example is hard coded
    var examples = requestExample.getExamples();
    assertThat(examples)
        .hasSize(1)
        .extracting("description")
        .contains(
            "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)");
  }

  @Test
  @DisplayName("should be able to get examples when asked for examples from stub")
  public void getExamplesFromMockedStub(@Mock ObtainExample obtainExample) {
    // Stub
    var example =
        Example.builder()
            .code(1L)
            .description(
                "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)")
            .build();
    Mockito.lenient().when(obtainExample.getAllExamples()).thenReturn(List.of(example));
    // hexagon
    var requestExample = new ExampleDomain(obtainExample);
    var examples = requestExample.getExamples();
    assertThat(examples)
        .hasSize(1)
        .extracting("description")
        .contains(
            "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)");
  }

  @Test
  @DisplayName("should be able to get example when asked for example by id from stub")
  public void getExampleByIdFromMockedStub(@Mock ObtainExample obtainExample) {
    // Given
    // Stub
    var code = 1L;
    var description =
        "I want to sleep\\r\\nSwat the flies\\r\\nSoftly, please.\\r\\n\\r\\n-- Masaoka Shiki (1867-1902)";
    var expectedExample = Example.builder().code(code).description(description).build();
    Mockito.lenient()
        .when(obtainExample.getExampleByCode(code))
        .thenReturn(Optional.of(expectedExample));
    // When
    var requestExample = new ExampleDomain(obtainExample);
    var actualExample = requestExample.getExampleByCode(code);
    assertThat(actualExample).isNotNull().isEqualTo(expectedExample);
  }

  @Test
  @DisplayName("should throw exception when asked for example by id that does not exists from stub")
  public void getExceptionWhenAskedExampleByIdThatDoesNotExist(@Mock ObtainExample obtainExample) {
    // Given
    // Stub
    var code = -1000L;
    Mockito.lenient().when(obtainExample.getExampleByCode(code)).thenReturn(Optional.empty());
    // When
    var requestExample = new ExampleDomain(obtainExample);
    // Then
    assertThatThrownBy(() -> requestExample.getExampleByCode(code))
        .isInstanceOf(ExampleNotFoundException.class)
        .hasMessageContaining("Example with code " + code + " does not exist");
  }
}
