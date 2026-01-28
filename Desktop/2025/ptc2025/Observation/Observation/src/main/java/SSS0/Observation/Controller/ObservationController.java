package SSS0.Observation.Controller;

import SSS0.Observation.Exceptions.ExceptionDatosDuplicados;
import SSS0.Observation.Exceptions.ExceptionObservationNotFound;
import SSS0.Observation.Models.DTO.ObservationDTO;
import SSS0.Observation.Service.ObservationService;
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
@RequestMapping("/apiObservation")
public class ObservationController {

    @Autowired
    ObservationService Service;

    @GetMapping("/consultarDatos")
    public List<ObservationDTO> ObtenerDatos() {
        return Service.ObtenerObservaciones();
    }

    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevaObservacion(@Valid @RequestBody ObservationDTO json, HttpServletRequest request) {
        try {
            ObservationDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar Observacion",
                    "detail", e.getMessage()
            ));
        }
    }


    @PutMapping("/editarObservacion/{id_observation}")//Url
    public ResponseEntity<?> modificarObservacion(@PathVariable Long id_observation , @Valid @RequestBody ObservationDTO json, @org.jetbrains.annotations.NotNull BindingResult bindingResult)
    {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar llamando al servicio y guaradar el resultado en un DTO
            ObservationDTO dto = Service.actualizarObservacion(id_observation, json);
            //SI sale bien laoperacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionObservationNotFound e) {
            //si  no existe, retornar HTTP 400 (not found)
            return ResponseEntity.notFound().build();
        } catch (ExceptionDatosDuplicados e) {
            //si hay datos duplicados retornar HTTP 409 (CONFLICT)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of(
                            "Error", "Datos duplicados",
                            "Campo", e.getCampoDuplicado()
                    ));
        }
    }

    @DeleteMapping("/eliminarObservacion/{id}")
    public ResponseEntity<?> eliminarObservacion(@PathVariable Long id){
        try{
            if(!Service.removerObservacion(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "observacion no encontrada")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "La observacion no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "obseevacion elminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Erro al eliminar observacion",
                    "detail", e.getMessage()
            ));
        }
    }
}