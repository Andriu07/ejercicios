package SSS0.Maintenance.Controller;

import SSS0.Maintenance.Exceptions.ExceptionDatosDuplicados;
import SSS0.Maintenance.Exceptions.ExceptionMaintenanceNotFound;
import SSS0.Maintenance.Models.DTO.MaintenanceDTO;
import SSS0.Maintenance.Service.MaintenanceService;
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
@RequestMapping("/apiMaintenace")
public class MaintenanceController {

     @Autowired
     MaintenanceService Service;


    @GetMapping("/consultarDatos")
    public List<MaintenanceDTO> ObtenerDatos() {
        return Service.ObtenerManteniminetos();
    }


    //inserccion de datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoMantenmiento(@Valid @RequestBody MaintenanceDTO json, HttpServletRequest request) {
        try {
            MaintenanceDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar mantenimiento",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarMantenimineto/{id_maintenance}")//Url
    public ResponseEntity<?> modificarMantenimiento(@PathVariable Long id_maintenance, @Valid @RequestBody MaintenanceDTO json, BindingResult bindingResult) {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar llamando al servicio y guaradar el resultado en un DTO
            MaintenanceDTO dto = Service.actualizarMantenimiento(id_maintenance, json);
            //SI sale bien laoperacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionMaintenanceNotFound e) {
            //si no existe, retornar HTTP 400 (not found)
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

    @DeleteMapping("/eliminarMantenimiento/{id_Mantenimiento}")
    public ResponseEntity<?> eliminarMantenimiento(@PathVariable Long id_maintenance) {
        try {
            if (!Service.removerMantenimiento(id_maintenance)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "mantenimiento no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "El mantenimiento no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Mantenimiento elminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar mantenimiento",
                    "detail", e.getMessage()
            ));
        }

    }
}