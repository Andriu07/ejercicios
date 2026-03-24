package SSSO.Type_Maintenance.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TYPE_MAINTENANCE")
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class TypeMaintenanceEntity {

    @Id
    @Column(name = "ID_TYPE_MAINTENANCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_maintenance")
    @SequenceGenerator(name = "seq_type_maintenance", sequenceName = "seq_type_maintenance", allocationSize = 1)
    private Long id_type_maintenance;

    @Column(name = "NAME_TYPE")
    private String name_type;
    @Column(name = "DESCRIPTION")
    private String description;
}
