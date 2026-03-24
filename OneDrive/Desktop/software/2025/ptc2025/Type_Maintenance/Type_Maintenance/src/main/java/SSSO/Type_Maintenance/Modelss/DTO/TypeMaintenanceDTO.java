package SSSO.Type_Maintenance.Modelss.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class TypeMaintenanceDTO {

    private Long id_type_maintenance;

    @NotBlank
    private String name_type;
    @
    NotBlank
    private String description;
}
