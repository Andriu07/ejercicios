package Employe_Brigade.Employe_Brigade.Controller;


import Employe_Brigade.Employe_Brigade.Models.DTO.Employee_BrigadeDTO;
import Employe_Brigade.Employe_Brigade.Service.Employee_Brigade.Employee_BrigadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@RestController
@RequestMapping("/ApiActionEmployee_Brigade")
public class Employee_BrigadeController {

    @Autowired
    private Employee_BrigadeService acceso;
    //creamos un objeto de Brigade service para obtener acceso a todos sus metodos

    @GetMapping("/ObtenerEmpleadoBrigada")
    public List<Employee_BrigadeDTO> DataEmployee_Brigade(){
        return acceso.getAllEmployee_Brigade();
    }

    @PostMapping("/IngresarBrigadaViaSP")
    public ResponseEntity<Map<String, Object>> InsertarEmpleadoBrigadaViaSP(@Valid @RequestBody Employee_BrigadeDTO employee_brigadeDTO){
        try {
            Employee_BrigadeDTO respuesta = acceso.InsertarEmpleadoBrigadaViaSP( employee_brigadeDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "succes",
                    "message", "Empleado Brigada creada exitosamente via SP con ID generado por API",
                    "data", respuesta
            ));
        } catch (Exception e) {
            System.err.println("Error al registrar empleadobrigada via SP: " + e.getMessage());
            e.printStackTrace();return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "succes",
                    "message", "Empleado Brigada creada exitosamente via SP con ID generado por API",
                    "data", e.getMessage()
            ));

        }
    }

    @PutMapping("ActualizarEmpleadoBrigadaViaSP/{id}")
    public ResponseEntity<Map<String, Object>> ActualizarmpleadoBrigadaViaSP(@PathVariable Long id, @Valid @RequestBody Employee_BrigadeDTO employee_brigadeDTO) {
        try {
            if (employee_brigadeDTO.getIdEmployee_Brigade() != null && !employee_brigadeDTO.getIdEmployee_Brigade().equals(id)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "error",
                        "message", "El id en la URL y el ID en el cuerpo no coinciden"
                ));

            }

            employee_brigadeDTO.setIdEmployee_Brigade(id);

            Employee_BrigadeDTO respuesta = acceso.ActualizarEmpleadoBrigadaViaSP(employee_brigadeDTO, id);
            return ResponseEntity.ok().body(Map.of(
                    "status", "succes",
                    "message", "empleadoBrigada actualizada exitosamente via SP",
                    "data", respuesta

            ));
        } catch (Exception e) {
            System.err.println("Error al registrar Empleado brigada via SP: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error interno del servidor al registrar empleado brigada via SP",
                    "data", e.getMessage()
            ));
        }
    }


    @DeleteMapping("/EliminarEmpleadoBrigada/{idEmployeBrigade}")
    public ResponseEntity<Void> deleteEmployee_Brigade(@PathVariable Long idEmployeeBrigade) {
        Employee_BrigadeService.deleteEmployee_Brigade(idEmployeeBrigade);
        return ResponseEntity.noContent().build();
    }

}
