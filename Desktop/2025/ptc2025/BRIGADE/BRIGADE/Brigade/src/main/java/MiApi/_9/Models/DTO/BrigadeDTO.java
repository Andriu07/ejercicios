package MiApi._9.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class BrigadeDTO {

    private Long id;

    @NotBlank(message = "No debe de ser nulo")
    private String nameBrigade;

    @Size(min = 10, message = "Debe contener almenos 10 caracteres")
    private Integer capacityStaff;

    @NotNull
    private Blob imgBrigade;

    @NotNull(message = "L descripcion de la brigada no puede estar vacia")
    private String description;
}
