package SSS0.Observation_Category.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Observation_CategoryDTO {

    private Long id_observation_category;

    @NotBlank
    private String nameObservationCategory;

    @NotBlank
    private String description;
}
