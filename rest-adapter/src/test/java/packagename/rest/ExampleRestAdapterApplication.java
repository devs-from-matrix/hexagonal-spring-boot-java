package packagename.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "packagename")
public class ExampleRestAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleRestAdapterApplication.class, args);
  }
}
