package packagename.repository.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import packagename.repository.entity.ExampleEntity;

@Repository
public interface ExampleDao extends JpaRepository<ExampleEntity, Long> {

  Optional<ExampleEntity> findByCode(Long code);
}
