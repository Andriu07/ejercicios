package MiApi._9.Entities;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Entity
@Table(name = "TB_BRIGADE")
@ToString
@EqualsAndHashCode
@Getter
@Setter


@NamedStoredProcedureQuery(
        name = "BrigadeEntity.insertBrigadeByProcedure", //un nombre unico para referenciarlo en JPA
        procedureName = "PKG_ACTIONS_BRIGADE.INSERT_BRIGADE",//NOMBRE DEL  paquete.procedimiento
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Brigade", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Name_Brigade", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Capacity_Staff", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Img_Brigade" , type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Description", type = Long.class),
        }
)

@NamedStoredProcedureQuery(
        name = "BriagdeEntity.updateBrigadeByProcedure", //un nombre unico para referenciarlo en JPA
        procedureName = "PKG_ACTIONS_BRIGADE.UPDATE_R",//NOMBRE DEL  paquete.procedimiento
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_Brigade", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Name_Brigade", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Capacity_Staff", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Img_Brigade" , type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_Description", type = Long.class),
        }
)
public class BrigadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_brigade")
    @SequenceGenerator(name = "seq_brigade", sequenceName = "seq_brigade", allocationSize = 1)
    @Column(name = "ID_BRIGADE")
    private Long id;

    @Column(name = "NAME_BRIGADE")
    private String nameBrigade;

    @Column(name = "CAPACITY_STAFF")
    private Integer capacityStaff;

    @Column(name = "IMG_BRIGADE")
    private Blob imgBrigade;

    @Column(name = "DESCRIPTION")
    private String description;


}
