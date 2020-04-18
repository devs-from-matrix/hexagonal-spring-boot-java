package packagename.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import packagename.domain.port.RequestExample;

@SpringBootApplication
@ComponentScan(basePackages = "packagename")
public class ExamplePoetryRestAdapterApplication {

  @MockBean
  private RequestExample requestExample;

  public static void main(String[] args) {
    SpringApplication.run(ExamplePoetryRestAdapterApplication.class, args);
  }
}
