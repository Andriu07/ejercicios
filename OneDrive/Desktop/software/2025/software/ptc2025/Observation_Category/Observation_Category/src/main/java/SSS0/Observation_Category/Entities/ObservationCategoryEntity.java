package SSS0.Observation_Category.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_OBSERVATION_CATEGORY")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ObservationCategoryEntity {

    @Id
    @Column(name = "ID_OBSERVATION_CATEGORY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_observation_category")
    @SequenceGenerator(name = "seq_observation_category" , sequenceName = "seq_observation_category", allocationSize = 1)
    private Long id_observation_category;

    @Column(name = "Name_ObservationCategory")
    private String nameObservationCategory;

    @Column(name = "DESCRIPTION")
    private String description;
}
