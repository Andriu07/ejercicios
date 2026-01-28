package SSS0.Maintenance.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Table(name = "TB_MAINTENANCE")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MaintenanceEntity {

    @Id
    @Column(name = "ID_MAINTENANCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_maintenance")
    @SequenceGenerator(name = "seq_maintenance" , sequenceName = "seq_maintenance", allocationSize = 1)
    private String id_maintenance;

    @Column(name = "DATE_MAINTENANCE")
    private LocalDate date_maintenance;

    @Column(name = "STATE_MAINTENANCE")
    private String state_maintenance;

    @Column(name = "ID_MEMBER")
    private Long id_member;

    @Column(name = "ID_LOCATION")
    private String id_location;
}
