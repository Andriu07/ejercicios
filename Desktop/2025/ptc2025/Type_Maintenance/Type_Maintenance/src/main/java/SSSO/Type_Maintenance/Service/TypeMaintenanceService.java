package SSSO.Type_Maintenance.Service;

import SSSO.Type_Maintenance.Entities.TypeMaintenanceEntity;
import SSSO.Type_Maintenance.Exceptions.ExceptionTypeMaintenanceNotFound;
import SSSO.Type_Maintenance.Exceptions.ExceptionTypeMaintenanceNotRegister;
import SSSO.Type_Maintenance.Modelss.DTO.TypeMaintenanceDTO;
import SSSO.Type_Maintenance.Repository.TypeMaintenanceRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TypeMaintenanceService {

    @Autowired
    TypeMaintenanceRepository repository;

    public List<TypeMaintenanceDTO> ObtenerTipodeMantenimiento() {
        List<TypeMaintenanceEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    private TypeMaintenanceDTO convertirADTO(TypeMaintenanceEntity typeMaintenanceEntity) {
        //creando objeto a retornar
        TypeMaintenanceDTO dto = new TypeMaintenanceDTO();
        dto.setId_type_maintenance(typeMaintenanceEntity.getId_type_maintenance());
        dto.setName_type(typeMaintenanceEntity.getName_type());
        dto.setDescription(typeMaintenanceEntity.getDescription());
        return dto;
    }

    public TypeMaintenanceDTO InsertarDatos(TypeMaintenanceDTO data) {
        if (data == null || data.getName_type() == null || data.getName_type().isEmpty()) {
            throw new IllegalArgumentException("Tipo de Mantenimineto y nombre de tipo no pueden ser nulos");
        }
        try {
            TypeMaintenanceEntity entity = ConvertirAEntity(data);
            TypeMaintenanceEntity tipodeManteniminetoguardado = repository.save(entity);
            return convertirADTO(tipodeManteniminetoguardado);
        } catch (Exception e) {
            log.error("Error al registrar el tipo detenimiento man: " + e.getMessage());
            throw new ExceptionTypeMaintenanceNotRegister("Error al registrrar tipo de mantenimiento");
        }
    }

    private TypeMaintenanceEntity ConvertirAEntity(TypeMaintenanceDTO data) {
        TypeMaintenanceEntity entity = new TypeMaintenanceEntity();
        entity.setId_type_maintenance(data.getId_type_maintenance());
        entity.setName_type(data.getName_type());
        entity.setDescription(data.getDescription());
        return entity;
    }


    public TypeMaintenanceDTO actualizarTipodeMantenimiento(Long id_type_maintenance, @Valid TypeMaintenanceDTO json) {
        //varificar la existencia de usuario
        TypeMaintenanceEntity existente = repository.findById(id_type_maintenance).orElseThrow(() -> new ExceptionTypeMaintenanceNotFound("Tipo de mantenimineto no encontrado"));
        //convertir los datos dto a entity
         existente.setId_type_maintenance(json.getId_type_maintenance());
         existente.setName_type(json.getName_type());
         existente.setDescription(json.getDescription());
        //guardar los nuevos valores
        TypeMaintenanceEntity tipodeMantenimientoActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(tipodeMantenimientoActualizado);
    }


    public boolean removerTipodeMantenimiento(Long id_type_maintenance) {
        try {
            TypeMaintenanceEntity existente = repository.findById(id_type_maintenance).orElse(null);
            if (existente != null) {
                repository.deleteById(id_type_maintenance);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro tipo de mantenimineto con el id  " + id_type_maintenance + "para eliminar.", 1);

        }
    }

}
