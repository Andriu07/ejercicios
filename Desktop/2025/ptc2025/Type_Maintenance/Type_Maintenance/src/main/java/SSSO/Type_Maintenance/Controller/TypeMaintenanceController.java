package SSSO.Type_Maintenance.Controller;

import SSSO.Type_Maintenance.Exceptions.ExceptionDatosDuplicados;
import SSSO.Type_Maintenance.Exceptions.ExceptionTypeMaintenanceNotFound;
import SSSO.Type_Maintenance.Modelss.DTO.TypeMaintenanceDTO;
import SSSO.Type_Maintenance.Service.TypeMaintenanceService;
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
@RequestMapping("/apiTypeMaintenance")
public class TypeMaintenanceController {

    @Autowired
    TypeMaintenanceService Service;


    @GetMapping("/consultarDatos")
    public List<TypeMaintenanceDTO> ObtenerDatos() {
        return Service.ObtenerTipodeMantenimiento();
    }


    //inserccion de datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoTipodeMantenimiento(@Valid @RequestBody TypeMaintenanceDTO json, HttpServletRequest request) {
        try {
            TypeMaintenanceDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar typo de mantenimiento",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarTipodeMantenimiento/{id_type_maintenance}")//Url
    public ResponseEntity<?> modificarTipodeMantenimineto(@PathVariable Long id_type_maintenance, @Valid @RequestBody TypeMaintenanceDTO json, BindingResult bindingResult) {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar  llamando al servicio y guaradar el resultado en un DTO
            TypeMaintenanceDTO dto = Service.actualizarTipodeMantenimiento(id_type_maintenance, json);
            //SI sale bien la operacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionTypeMaintenanceNotFound e) {
            //si el no existe, retornar HTTP 400 (not found)
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

    @DeleteMapping("/eliminarTipodeMantenimiento/{id_type_maintenance}")
    public ResponseEntity<?> eliminarTipodeMantenimineto(@PathVariable Long id_type_maintenance) {
        try {
            if (!Service.removerTipodeMantenimiento(id_type_maintenance)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", " Tipo de mantenimineto no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "El tipo de mantenimiento no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Tipo de mantenimineto elminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar tipo de mantenimineto",
                    "detail", e.getMessage()
            ));
        }

    }
}