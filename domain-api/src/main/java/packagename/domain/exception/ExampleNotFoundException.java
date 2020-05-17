package packagename.domain.exception;

public class ExampleNotFoundException extends RuntimeException {
  private Long id;

  public ExampleNotFoundException(Long id) {
    super("Example with code " + id + " does not exist");
  }
}

