package apiPractica.Practica.deRepasopERSONAL.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ExpoDTO {

    private Long id;

    @NotBlank
    private String Nombre;

    @NotBlank
    private String Apellido;

    @Size(message = "La contraseña debe contener almenos 8 caracteres")
    private String Contrasena;

    @NotNull(message = "El cargo no debe de ser nulo")
    @Positive(message = "El ID del cargo debe ser positivo")
    private Long id_rol;
}
