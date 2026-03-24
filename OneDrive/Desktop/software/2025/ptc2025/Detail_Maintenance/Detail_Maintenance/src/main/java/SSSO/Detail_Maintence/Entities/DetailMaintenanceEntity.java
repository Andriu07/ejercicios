package SSSO.Detail_Maintence.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_DETAIL_MAINTENANCE")
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class DetailMaintenanceEntity {

    @Id
    @Column(name = "ID_DETAIL_MAINTENANCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detail_maintenance")
    @SequenceGenerator(name = "seq_detail_maintenance", sequenceName = "seq_detail_maintenance", allocationSize = 1)
    private Long id_detail_maintenance;

    @Column(name = "NAME_MAINTENANCE")
    private String name_maintenance;

    @Column(name = "TOTAL_TIME")
    private String total_time;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVITY")
    private String activity;

    @Column(name = "IMG_MAINTENANCE")
    private String img_maintenance;

    @Column(name = "ID_ASSIGN_MAINTENANCE")
    private Long id_assign_maintenance;

    @Column(name = "ID_TYPE_MAINTENANCE")
    private Long id_type_Maintenance;

}
