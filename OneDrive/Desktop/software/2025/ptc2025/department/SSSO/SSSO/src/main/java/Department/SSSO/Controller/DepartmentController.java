package Department.SSSO.Controller;

import Department.SSSO.Exceptions.ExceptionDatosDuplicados;
import Department.SSSO.Exceptions.ExceptionDepartamentoNoEncontrado;
import Department.SSSO.Models.DTO.DepartmentDTO;
import Department.SSSO.Service.DepartmentService;
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
@RequestMapping("/apiDepartament")
public class DepartmentController {

    @Autowired
    DepartmentService Service;

    //obtener datos
    @GetMapping("/ObtenerDepartamento")
    public List<DepartmentDTO> ObtenerDatos(){
        return Service.ObtenerDepartamentos();
    }

    //insertar datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoDepartamento(@Valid @RequestBody DepartmentDTO json, HttpServletRequest request) {
        try {
            DepartmentDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar usuario",
                    "detail", e.getMessage()
            ));
        }

    }

    //actualizar datos
    @PutMapping("/editarDepartamento/{id}")
    public ResponseEntity<?> modificrDepartamento(@PathVariable Long id_department, @Valid @RequestBody DepartmentDTO json, BindingResult bindingResult) {
        //verificar si hay errores de validacion{e}: campos vacios,formatos incorrectos
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            //intetar actualizar el departameto llamando al servicio y guaradar el resultado en un DTO
            DepartmentDTO dto = Service.actualizarDepartamento(id_department, json);
            //SI sale bien HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionDepartamentoNoEncontrado e) {
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

    @DeleteMapping("/eliminarDepartamento/{id}")
    public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id_department) {
        try {
            if (!Service.removerDepartamento(id_department)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "Departamento no encontrado")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "El departamento no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Departamento elminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar departamento",
                    "detail", e.getMessage()
            ));
        }

    }
}
