package packagename.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;
import packagename.repository.dao.ExampleDao;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ExampleJpaTest {

  @Autowired private ObtainExample obtainExample;
  @Autowired private ExampleDao exampleDao;

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

  @Test
  @DisplayName("should save given example in database")
  public void shouldSaveGivenExampleInDatabase() {
    // Given
    Example example = new Example(123L, "Jhonny jhonny! yes papa");
    // When
    var savedExample = obtainExample.saveExample(example);
    // Then
    assertThat(savedExample).isNotNull().isNotEmpty().get().isEqualTo(example);
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName("should delete example by given example code from database")
  public void shouldDeleteExampleByGivenExampleCodeFromDatabase() {
    // Given from @Sql
    // When
    obtainExample.deleteExampleByCode(1L);
    // Then
    assertThat(exampleDao.findByCode(1L)).isEmpty();
  }

  @Test
  @DisplayName("should give error when asked to delete an example which does not exist in database")
  public void shouldGiveErrorWhenAskedToDeleteAnExampleWhichDoesNotExistInDatabase() {
    // Given
    Long exampleCode = 1L;
    // When //Then
    assertThatThrownBy(() -> obtainExample.deleteExampleByCode(exampleCode))
        .isInstanceOf(ExampleNotFoundException.class)
        .hasMessageContaining("Example with code " + exampleCode + " does not exist");
  }
}
