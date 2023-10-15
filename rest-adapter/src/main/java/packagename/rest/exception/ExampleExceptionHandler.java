package packagename.rest.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import packagename.domain.exception.ExampleNotFoundException;
import packagename.rest.generated.model.ProblemDetail;

@RestControllerAdvice(basePackages = {"packagename"})
public class ExampleExceptionHandler {

  @ExceptionHandler(value = ExampleNotFoundException.class)
  public final ResponseEntity<ProblemDetail> handleExampleNotFoundException(
      final Exception exception, final WebRequest request) {
    var problem =
        ProblemDetail.builder()
            .type("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404")
            .status(HttpStatus.NOT_FOUND.value())
            .title("Example not found")
            .detail(exception.getMessage())
            .instance(((ServletWebRequest) request).getRequest().getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
  }
}
