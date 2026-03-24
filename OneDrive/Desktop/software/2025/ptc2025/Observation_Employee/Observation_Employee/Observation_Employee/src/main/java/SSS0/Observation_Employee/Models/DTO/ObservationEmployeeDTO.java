package SSS0.Observation_Employee.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ObservationEmployeeDTO {

    private Long id_observation_employee;

    @NotNull
    private LocalDate dt_report;

    @NotNull
    private Long Id_Observation;

    @NotBlank
    private String nit_employee;

    @NotNull
    private Long id_observation_category;
}
