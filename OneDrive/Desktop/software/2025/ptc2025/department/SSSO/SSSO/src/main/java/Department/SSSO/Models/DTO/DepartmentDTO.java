package Department.SSSO.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class DepartmentDTO {

    private Long id_department;

    @NotBlank
    private String nameDepartment;

    @NotBlank
    private String descriptionDepartment;

    @NotBlank
    private String logoDepartment;
}
