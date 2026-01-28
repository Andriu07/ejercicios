package SSS0.Observation_Report.Models.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Observation_ReportDTO {

    @NotNull
    private Long id_observation_report;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String description;

    @NotNull
    private Long id_observation_employee;
}
