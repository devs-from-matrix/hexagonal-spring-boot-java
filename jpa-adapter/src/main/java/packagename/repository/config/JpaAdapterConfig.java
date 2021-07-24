package packagename.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import packagename.domain.port.ObtainExample;
import packagename.repository.ExampleRepository;
import packagename.repository.dao.ExampleDao;

@Configuration
@EntityScan("packagename.repository.entity")
@EnableJpaRepositories(
    basePackages = "packagename.repository.dao",
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class JpaAdapterConfig {

  @Bean
  public ObtainExample getExampleRepository(ExampleDao exampleDao) {
    return new ExampleRepository(exampleDao);
  }
}
