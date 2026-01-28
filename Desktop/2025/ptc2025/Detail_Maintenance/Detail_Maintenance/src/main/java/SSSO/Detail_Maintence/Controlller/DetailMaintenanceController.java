package SSSO.Detail_Maintence.Controlller;

import SSSO.Detail_Maintence.Exceptions.ExceptionDatosDuplicados;
import SSSO.Detail_Maintence.Exceptions.ExceptionDetail_MaintenanceNotFound;
import SSSO.Detail_Maintence.Models.DTO.DetailMaintenanceDTO;
import SSSO.Detail_Maintence.Service.DetailMaintenanceService;
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

public class DetailMaintenanceController {

    @Autowired
    DetailMaintenanceService Service;


    @GetMapping("/consultarDatos")
    public List<DetailMaintenanceDTO> ObtenerDatos() {
        return Service.ObtenerDetallesMantenimiento();

    }


    //inserccion de datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoDetalleMantenimiento(@Valid @RequestBody DetailMaintenanceDTO json, HttpServletRequest request) {
        try {
            DetailMaintenanceDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar detalle mantenimiento",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarDetalleMantenimiento/{id_detail_maintenancer}")//Url
    public ResponseEntity<?> modificarDetalleMantenimiento(@PathVariable Long id_detail_maintenance, @Valid @RequestBody DetailMaintenanceDTO json, BindingResult bindingResult) {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar llamando al servicio y guaradar el resultado en un DTO
            DetailMaintenanceDTO dto = Service.actualizarDetalleMantenimiento(id_detail_maintenance, json);
            //SI sale bien laoperacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionDetail_MaintenanceNotFound e) {
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

    @DeleteMapping("/eliminarDetalleMantenimiento/{id_detail_maintenance}")
    public ResponseEntity<?> eliminarDetalleMantenimiento(@PathVariable Long id_detail_maintenance) {
        try {
            if (!Service.removerDetalleMantenimiento(id_detail_maintenance)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "Detalle mantenimiento  no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "El detalle de manetenimiento no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Detalle mantenimiento elminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar detalle de mantenimiento",
                    "detail", e.getMessage()
            ));
        }


    }
}