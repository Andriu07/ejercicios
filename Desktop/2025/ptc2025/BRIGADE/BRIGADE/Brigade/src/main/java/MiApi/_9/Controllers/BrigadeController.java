package MiApi._9.Controllers;
import MiApi._9.Models.DTO.BrigadeDTO;

import MiApi._9.Services.Brigade.BrigadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@RestController
@RequestMapping("/ApiActionBrigade")
public class BrigadeController {

    @Autowired
    private BrigadeService acceso;
    //creamos un objeto de Brigade service para obtener acceso a todos sus metodos

    @GetMapping("/ObtenerBrigada")
    public List<BrigadeDTO>DataBrigade(){
        return acceso.getAllBrigade();
    }

    @PostMapping("/IngresarBrigadaViaSP")
    public ResponseEntity<Map<String, Object>> RegistrarBrigadaViaSP(@Valid @RequestBody BrigadeDTO brigadeDTO){
        try {
            BrigadeDTO respuesta = acceso.InsertarBrigadaViaSP(brigadeDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
               "status", "succes",
               "message", "Brigada creada exitosamente via SP con ID generado por API",
               "data", respuesta
            ));
        } catch (Exception e) {
            System.err.println("Error al registrar brigada via SP: " + e.getMessage());
            e.printStackTrace();return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "succes",
                    "message", "Brigada creada exitosamente via SP con ID generado por API",
                    "data", e.getMessage()
            ));

        }
    }

     @PutMapping("ActualizarBrigadaViaSP/{id}")
     public ResponseEntity<Map<String, Object>> ActualizarBrigadaViaSP(@PathVariable Long id, @Valid @RequestBody BrigadeDTO brigadeDTO) {
         try {
             if ((brigadeDTO.getId() != null) && !brigadeDTO.getId().equals(id)) {
                 return ResponseEntity.badRequest().body(Map.of(
                         "status", "error",
                         "message", "El id en la URL y el ID en el cuerpo no coinciden"
                 ));

             }

             brigadeDTO.setId(id);

             BrigadeDTO respuesta = acceso.ActualizarBrigadaViaSP(brigadeDTO, id);
             return ResponseEntity.ok().body(Map.of(
                     "status", "succes",
                     "message", "Brigada actualizada exitosamente via SP",
                     "data", respuesta

             ));
         } catch (Exception e) {
             System.err.println("Error al registrar brigada via SP: " + e.getMessage());
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                     "status", "error",
                     "message", "Error interno del servidor al registrar brigada via SP",
                     "data", e.getMessage()
             ));
         }
     }


    @DeleteMapping("/EliminarBrigada/{id}")
    public ResponseEntity<Void> deleteBrigade(@PathVariable Long id) {
        BrigadeService.deleteBrigade(id);
        return ResponseEntity.noContent().build();
    }
}


