package packagename.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import packagename.domain.ExampleDomain;
import packagename.domain.port.ObtainExample;
import packagename.domain.port.RequestExample;
import packagename.repository.config.JpaAdapterConfig;

@Configuration
@Import(JpaAdapterConfig.class)
public class BootstrapConfig {

  @Bean
  public RequestExample getRequestExample(ObtainExample obtainExample) {
    return new ExampleDomain(obtainExample);
  }
}
