package Employe_Brigade.Employe_Brigade.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_EMPLOYEE_BRIGADE")
@ToString
@EqualsAndHashCode
@Getter
@Setter

@NamedStoredProcedureQuery(
        name = "Employee_BrigadeEntity.insertEmployeeBrigadeByProcedure", //un nombre unico para referenciarlo en JPA
        procedureName = "PKG_ACTIONS_EMPLOYEE_BRIGADE.INSERT_EMPLOYEE_BRIGADE",//NOMBRE DEL  paquete.procedimiento
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Employee_Brigade", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Nit_Employee_Brigade", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Brigade", type = Long.class),
        }
)

@NamedStoredProcedureQuery(
        name = "EmployeeBriagdeEntity.updateEmployeeBrigadeByProcedure", //un nombre unico para referenciarlo en JPA
        procedureName = "PKG_ACTIONS_EMPLOYEEBRIGADE.UPDATE_EMPLOYEE_BRIGADE",//NOMBRE DEL  paquete.procedimiento
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Employee_Brigade", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Nit_Employee_Brigade", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Brigade", type = Long.class),
        }
)

public class Employee_BrigadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_brigade")
    @SequenceGenerator(name = "seq_employee_brigade", sequenceName = "seq_employee_brigade", allocationSize = 1)

    @Column(name = "ID_EMPLOYEE_BRIGADE")
    private Long idEmployee_Brigade;

    @Column(name = "NIT_EMPLOYEE_BRIGADE")
    private String nitBrigade;

    @Column(name = "ID_BRIGADE")
    private Long  idBrigade;
}
