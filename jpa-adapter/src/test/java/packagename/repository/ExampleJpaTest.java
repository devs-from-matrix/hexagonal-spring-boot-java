package packagename.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ExampleJpaTest {

  @Autowired private ObtainExample obtainExample;

  @Test
  @DisplayName("should start the application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given examples exist in database when asked should return all examples from database")
  public void shouldGiveMeExamplesWhenAskedGivenExampleExistsInDatabase() {
    // Given from @Sql
    // When
    var examples = obtainExample.getAllExamples();
    // Then
    assertThat(examples)
        .isNotNull()
        .extracting("description")
        .contains("Twinkle twinkle little star");
  }

  @Test
  @DisplayName("given no examples exists in database when asked should return empty")
  public void shouldGiveNoExampleWhenAskedGivenExamplesDoNotExistInDatabase() {
    // When
    var examples = obtainExample.getAllExamples();
    // Then
    assertThat(examples).isNotNull().isEmpty();
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given examples exists in database when asked for example by id should return the example")
  public void shouldGiveTheExampleWhenAskedByIdGivenThatExampleByThatIdExistsInDatabase() {
    // Given from @Sql
    // When
    var example = obtainExample.getExampleByCode(1L);
    // Then
    assertThat(example)
        .isNotNull()
        .isNotEmpty()
        .get()
        .isEqualTo(Example.builder().code(1L).description("Twinkle twinkle little star").build());
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given examples exists in database when asked for example by id that does not exist should give empty")
  public void shouldGiveNoExampleWhenAskedByIdGivenThatExampleByThatIdDoesNotExistInDatabase() {
    // Given from @Sql
    // When
    var example = obtainExample.getExampleByCode(-1000L);
    // Then
    assertThat(example).isEmpty();
  }
}
