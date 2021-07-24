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

@Table(name = "REVISION_INFO", schema = "EXAMPLE_AUDIT")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
    schema = "EXAMPLE_AUDIT",
    name = "SEQ_REVISION_INFO",
    sequenceName = "EXAMPLE_AUDIT.SEQ_REVISION_INFO",
    allocationSize = 1)
public class EnversRevisionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVISION_INFO")
  @Column(name = "REV")
  private Long rev;

  @Column(name = "TIMESTAMP")
  private Long code;
}
