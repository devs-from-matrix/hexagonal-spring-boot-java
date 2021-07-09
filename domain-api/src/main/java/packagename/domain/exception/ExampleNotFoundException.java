package packagename.domain.exception;

public class ExampleNotFoundException extends RuntimeException {

  public ExampleNotFoundException(Long id) {
    super("Example with code " + id + " does not exist");
  }
}
