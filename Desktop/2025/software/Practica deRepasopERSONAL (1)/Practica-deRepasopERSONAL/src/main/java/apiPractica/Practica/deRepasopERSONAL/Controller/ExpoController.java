package apiPractica.Practica.deRepasopERSONAL.Controller;

import apiPractica.Practica.deRepasopERSONAL.Exceptions.ExceptionMemberNotFound;
import apiPractica.Practica.deRepasopERSONAL.Exceptions.ExcpetionDuplicateData;
import apiPractica.Practica.deRepasopERSONAL.Models.DTO.ExpoDTO;
import apiPractica.Practica.deRepasopERSONAL.Service.ExpoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiexpo")
public class ExpoController {
    @Autowired
    ExpoService Service;

    @GetMapping
    public List<ExpoDTO> ObtenerDatos(){
        return Service.retornarMiembrosExpo();
    }

    @PostMapping("/registrarNuevoMiembro")
    public ResponseEntity<?> nuevoMiembro(@Valid @RequestBody ExpoDTO json, HttpServletRequest request) {
        try {
            ExpoDTO respuesta = Service.InsertarDatos(json);
            if (respuesta == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "insercción fallida",
                        "errorType", "ValidationError",
                        "message", "Los datos no pudieron ser insertados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "succes",
                    "data", respuesta
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error no controlado al registrar miembro expo",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarMiembro/{id}")
    public ResponseEntity<?> actualizarMiembro(@PathVariable Long id, @Valid @RequestBody ExpoDTO json, BindingResult bindingResult){
        //verificar si hay errores mandando a llamar al service
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            ExpoDTO dto = Service.ActualizarMiembro(id, json);
            return ResponseEntity.ok(dto);//si sale bien retornar HTTP 200(OK)
        }catch (ExceptionMemberNotFound e){
            return ResponseEntity.notFound().build();//si no se encontro retornar HTTP (400) NOT FOUND
        }catch (ExcpetionDuplicateData e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(// si hay datos duplicados retornar HTTP(409) CONFLICT
                    "Error","Datos Duplicados",
                    "Campo", e.getDuplicateData()
            ));
        }
    }


    @DeleteMapping("/eliminarMiembro")
    public ResponseEntity<?> eliminarMiembro(@PathVariable Long id){
        try{
            if(Service.removerMiembro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("mensaje de error", "miembro no encontrado").body(Map.of(
                        "Error", "NOT FOUND",
                        "Message"," El miembro  no fue encontrado",
                        "timestamp", Instant.now().toString()
                ));
            }
             return ResponseEntity.ok().body(Map.of(
                     "status","Proceso completado",
                     "messge", "miembro eliminado exitosamente "
             ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message","Error al eliminar miembro",
                    "detail", e.getMessage()
            ));
    }
    }
}
