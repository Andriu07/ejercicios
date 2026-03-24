package SSS0.Observation.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_OBSERVATION")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ObservationEntity {

    @Id
    @Column(name = "ID_OBSERVATION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_observation")
    @SequenceGenerator(name ="seq_observation" , sequenceName = "seq_observation" , allocationSize = 1)
    private Long id_observation;

    @Column(name = "NAME_OBSERVATION")
    private String nameObservation;
}
