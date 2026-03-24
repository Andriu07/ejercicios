package SSS0.Observation_Employee.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "TB_OBSERVATION_EMPLOYEE")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ObservationEmployeeEntity {

    @Id
    @Column(name = "ID_OBSERVATION_EMPLOYEE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_observation_employee")
    @SequenceGenerator( name = "seq_observation_employee" , sequenceName = "seq_observation_employee", allocationSize = 1)
    private Long id_observation_employee;

    @Column(name = "DT_REPORT")
    private LocalDate dt_report;

    @Column(name = "ID_OBSERVATION")
    private Long Id_Observation;

    @Column(name = "NIT_EMPLOYEE")
    private String nit_employee;

    @Column(name = "ID_OBSERVATION_CATEGORY")
    private Long id_observation_category;
}
