package packagename.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import packagename.domain.model.Example;

@Table(name = "T_EXAMPLE")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_EXAMPLE")
  @SequenceGenerator(name = "SEQ_T_EXAMPLE", sequenceName = "SEQ_T_EXAMPLE", allocationSize = 1, initialValue = 1)
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
