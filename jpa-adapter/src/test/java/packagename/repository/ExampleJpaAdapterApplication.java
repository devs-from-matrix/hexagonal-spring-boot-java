package packagename.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import packagename.repository.config.JpaAdapterConfig;

@SpringBootApplication
public class ExampleJpaAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleJpaAdapterApplication.class, args);
  }

  @TestConfiguration
  @Import(JpaAdapterConfig.class)
  static class ExampleJpaTestConfig {}
}
