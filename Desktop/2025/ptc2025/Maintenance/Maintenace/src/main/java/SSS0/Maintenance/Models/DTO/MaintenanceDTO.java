package SSS0.Maintenance.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class MaintenanceDTO {

    private String id_maintenance;

    @NotNull
    private LocalDate date_maintenance;

    @NotBlank
    private String state_maintenance;

    @NotNull
    private Long id_member;

    @NotBlank
    private String id_location;
}
