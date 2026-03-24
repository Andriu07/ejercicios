package SSS0.Observation_Category.Controller;

import SSS0.Observation_Category.Excpetions.ExceptionDatosDuplicados;
import SSS0.Observation_Category.Excpetions.ExceptionObservationCategoryNotFound;
import SSS0.Observation_Category.Models.DTO.Observation_CategoryDTO;
import SSS0.Observation_Category.Service.Observation_CategoryService;
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
@RequestMapping("/apiObservationCategory")
public class Observation_CategoryController {

    @Autowired
    Observation_CategoryService Service;

    @GetMapping("/consultarDatos")
    public List<Observation_CategoryDTO> ObtenerDatos() {
        return Service.ObtenerCategoriasdeObservacion();
    }

    //inserccion de datos
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevaCategoriadeObservacion(@Valid @RequestBody Observation_CategoryDTO json, HttpServletRequest request) {
        try {
            Observation_CategoryDTO respuesta = Service.InsertarDatos(json);
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
                    "message", "error no controlado al registrar categoria de observacion",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarCategoriaObservacion/{id_observation_category}")//Url
    public ResponseEntity<?> modificarCategoriaObservacion(@PathVariable Long id_observation_category, @Valid @RequestBody Observation_CategoryDTO json, BindingResult bindingResult)
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
            Observation_CategoryDTO dto = Service.actualizarCategoriaObservacion(id_observation_category, json);
            //SI sale bien la operacion retornar HTTP 200 (ok) con el DTO actualizado
            return ResponseEntity.ok(dto);
        } catch (ExceptionObservationCategoryNotFound e) {
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

    @DeleteMapping("/eliminarCategoriadeObservacion/{id_observation_category}")
    public ResponseEntity<?> eliminarCategorideObservacion(@PathVariable Long id_observation_category) {
        try {
            if (!Service.removerCategoriadeObservacion(id_observation_category)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("mensaje de error", "Categoria de Observacion no encontrada")
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Message", "Categoria de Observacion no fue encontrada ",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Categoria de observacion elminada exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar categoria de observacion",
                    "detail", e.getMessage()
            ));
        }
    }
}
