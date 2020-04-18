package packagename;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import packagename.domain.ExampleDomain;
import packagename.domain.port.ObtainExample;
import packagename.domain.port.RequestExample;
import packagename.repository.config.JpaAdapterConfig;

@SpringBootApplication
public class ExampleE2EApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleE2EApplication.class);
  }

  @TestConfiguration
  @Import(JpaAdapterConfig.class)
  static class ExampleConfig {

    @Bean
    public RequestExample getRequestExample(final ObtainExample obtainExample) {
      return new ExampleDomain(obtainExample);
    }
  }
}
