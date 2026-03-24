package Employe_Brigade.Employe_Brigade.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter

public class Employee_BrigadeDTO {

        private Long idEmployee_Brigade;

        @NotBlank(message = "No debe de ser nulo")
        private String Nit_Employee_Brigade;

        @NotNull(message = "El ID de la brigada no puede estar vacio")
        private Long idBrigade;

}
