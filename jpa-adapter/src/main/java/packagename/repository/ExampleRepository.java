package packagename.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import packagename.domain.model.Example;
import packagename.domain.port.ObtainExample;
import packagename.repository.dao.ExampleDao;
import packagename.repository.entity.ExampleEntity;

public class ExampleRepository implements ObtainExample {

  private final ExampleDao exampleDao;

  public ExampleRepository(ExampleDao exampleDao) {
    this.exampleDao = exampleDao;
  }

  @Override
  public List<Example> getAllExamples() {
    return exampleDao.findAll().stream().map(ExampleEntity::toModel).collect(Collectors.toList());
  }

  @Override
  public Optional<Example> getExampleByCode(Long code) {
    var exampleEntity = exampleDao.findByCode(code);
    return exampleEntity.map(ExampleEntity::toModel);
  }

  @Override
  public Optional<Example> saveExample(Example example) {
    var exampleEntity =
        ExampleEntity.builder()
            .code(example.getCode())
            .description(example.getDescription())
            .build();
    var savedExample = exampleDao.save(exampleEntity);
    return Optional.of(savedExample.toModel());
  }
}
