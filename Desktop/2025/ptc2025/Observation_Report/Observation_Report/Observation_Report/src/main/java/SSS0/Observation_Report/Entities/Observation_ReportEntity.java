package SSS0.Observation_Report.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="OBSERVATION_REPORT")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Observation_ReportEntity {

    @Id
    @Column(name = "ID_OBSERVATION_REPORT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_observation_report")
    @SequenceGenerator(name ="seq_observation_report" , sequenceName = "seq_observation_report" , allocationSize = 1)
    private Long id_observation_report;

    @Column(name = "DATE_Report")
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ID_OBSERVATION_EMPLOYEE")
    private Long id_observation_employee;
}
