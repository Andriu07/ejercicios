package SSS0.Observation.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ObservationDTO {

    private Long id_obervation;

    @NotBlank
    private String name_observation;
}
