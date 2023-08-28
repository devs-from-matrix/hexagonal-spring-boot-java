package packagename.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import packagename.domain.model.Example;

@Table(name = "T_EXAMPLE")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ExampleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_EXAMPLE")
  @SequenceGenerator(
      name = "SEQ_T_EXAMPLE",
      sequenceName = "SEQ_T_EXAMPLE",
      allocationSize = 1,
      initialValue = 1)
  @Column(name = "TECH_ID")
  private Long techId;

  @Column(name = "CODE")
  private Long code;

  @Column(name = "DESCRIPTION")
  private String description;

  public Example toModel() {
    return Example.builder().code(code).description(description).build();
  }
}
