package packagename.repository;

import java.util.List;
import java.util.stream.Collectors;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;
import packagename.repository.dao.ExampleDao;
import packagename.repository.entity.ExampleEntity;

public class ExampleRepository implements ObtainExample {

  private ExampleDao exampleDao;

  public ExampleRepository(ExampleDao exampleDao) {
    this.exampleDao = exampleDao;
  }

  @Override
  public List<Example> getAllExamples() {
    return exampleDao.findAll().stream().map(ExampleEntity::toModel).collect(Collectors.toList());
  }
}
