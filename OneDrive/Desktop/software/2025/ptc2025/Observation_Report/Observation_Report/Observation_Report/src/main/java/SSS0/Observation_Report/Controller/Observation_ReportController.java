package SSS0.Observation_Report.Controller;

import SSS0.Observation_Report.Exceptions.ExceptionDatosDuplicados;
import SSS0.Observation_Report.Exceptions.ExceptionObservationReportNotFound;
import SSS0.Observation_Report.Models.DTO.Observation_ReportDTO;
import SSS0.Observation_Report.Service.Observation_ReportService;
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
@RequestMapping("/apiObservation_Report")
public class Observation_ReportController {

    @Autowired
    Observation_ReportService Service;

    @GetMapping("/consultarDatos")
    public List<Observation_ReportDTO> ObtenerDatos() {
        return Service.ObtenerReportedeObservaciones();
    }


    //inserccion de datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoReportedeObservación(@Valid @RequestBody Observation_ReportDTO json, HttpServletRequest request) {
        try {
                Observation_ReportDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar Reporte de observación",
                    "detail", e.getMessage()
            ));
        }
    }


    @PutMapping("/editarReportdedeObservación/{id}")//Url
    public ResponseEntity<?> modificarReportedeObservacion(@PathVariable Long id_observation_report, @Valid @RequestBody Observation_ReportDTO json, BindingResult bindingResult)
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
            Observation_ReportDTO dto = Service.actualizarReportedeObservación(id_observation_report, json);
            //SI sale bien laoperacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionObservationReportNotFound e) {
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

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<?> eliminarReportedeObservacion(@PathVariable Long id_observation_report){
        try{
            if(!Service.removerReportedeObservación(id_observation_report)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "Reporte de observación no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "El Reporte de observación no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Reporte elminado exitosamente"
            ));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar Reporte",
                    "detail", e.getMessage()
            ));
        }


    }
}