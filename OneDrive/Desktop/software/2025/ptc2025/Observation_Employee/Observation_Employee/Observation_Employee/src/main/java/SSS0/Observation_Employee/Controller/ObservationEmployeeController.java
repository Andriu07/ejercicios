package SSS0.Observation_Employee.Controller;


import SSS0.Observation_Employee.Excptions.ExceptionDatosDuplicados;
import SSS0.Observation_Employee.Excptions.ExceptionObservationEmployeeNotFound;
import SSS0.Observation_Employee.Models.DTO.ObservationEmployeeDTO;
import SSS0.Observation_Employee.Service.ObservationEmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiObservationEmployee")
public class ObservationEmployeeController {

    @Autowired
    ObservationEmployeeService Service;

    @GetMapping("/ConsultarDatos")
    public List<ObservationEmployeeDTO> Obtenerdatos(){
        return Service.ObtenerObaservacionesEmpleado();
    }

    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevaObservacionEmpleado(@Valid @RequestBody ObservationEmployeeDTO json, HttpServletRequest request){
        try {
            ObservationEmployeeDTO respuesta = Service.InsertarDatos(json);
            if (respuesta == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "inserccion fallida",
                        "errorType", "Validation_error",
                        "message", "Los datos no pudieron ser registrados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "succes",
                    "data", respuesta
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "error no controlado al registrar observacion  de empleado",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarObservacionEmpleado/{id_observation_employee}")//Url
    public ResponseEntity<?> modificarObservacionEmpleado(@PathVariable Long id_observation_employee, @Valid @RequestBody ObservationEmployeeDTO json, BindingResult bindingResult)
    {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar  llamando al servicio y guaradar el resultado en un DTO
            ObservationEmployeeDTO dto = Service.actualizarObservacionEmpleado(id_observation_employee, json);
            //SI sale bien laoperacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionObservationEmployeeNotFound e) {
            //si  no existe, retornar HTTP 400 (not found)
            return ResponseEntity.notFound().build();
        } catch (ExceptionDatosDuplicados e) {
            //si hay datos duplicados retornar HTTP 409 (CONFLICT)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of(
                            "Error", "Datos duplicados",
                            "Campo", e.getCampoDuplicado()
                    )
            );
        }
    }

    @DeleteMapping("/eliminarObservacionEmpleado/{id}")
    public ResponseEntity<?> eliminarObservacionEmpleado(@PathVariable Long id_observation_employee){
        try{
            if(!Service.removerObservacionEmpleado(id_observation_employee)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "Observacion empleado no encontrada")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "La observacion empleado no fue encontrada",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Observacion empleado elminado exitosamente"
            ));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar observacion empleado",
                    "detail", e.getMessage()
            ));
        }
    }
}
