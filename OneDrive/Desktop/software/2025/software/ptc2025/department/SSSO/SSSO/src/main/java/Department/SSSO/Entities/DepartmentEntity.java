package Department.SSSO.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "TB_DEPARTMENT")
@EqualsAndHashCode
@ToString
public class DepartmentEntity {

    @Id
    @Column(name = "ID_DEPARTMENT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_department")
    @SequenceGenerator(name ="seq_department" , sequenceName = "seq_department" , allocationSize = 1)
    private Long id_department;

    @Column(name = "NAME_DEPARTMENT")
    private String nameDepartment;

    @Column(name = "DESCRIPTION_DEPARTMENT")
    private String descriptionDepartment;

    @Column(name = "LOGO_DEPARTMENT")
    private String logoDepartment;
}
