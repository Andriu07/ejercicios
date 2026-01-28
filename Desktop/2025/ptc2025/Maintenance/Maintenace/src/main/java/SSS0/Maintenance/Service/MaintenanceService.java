package SSS0.Maintenance.Service;

import SSS0.Maintenance.Entities.MaintenanceEntity;
import SSS0.Maintenance.Exceptions.ExceptionMaintenanceNotFound;
import SSS0.Maintenance.Exceptions.ExceptionMaintenanceNotRegister;
import SSS0.Maintenance.Models.DTO.MaintenanceDTO;
import SSS0.Maintenance.Repository.MaintenanceRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MaintenanceService {

    @Autowired
    MaintenanceRepository repository;

    public List<MaintenanceDTO> ObtenerManteniminetos(){
        List<MaintenanceEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    private MaintenanceDTO convertirADTO(MaintenanceEntity maintenanceEntity) {
        //creando objeto a retornar
        MaintenanceDTO dto = new MaintenanceDTO();
        dto.setId_maintenance(maintenanceEntity.getId_maintenance());
        dto.setDate_maintenance(maintenanceEntity.getDate_maintenance());
        dto.setState_maintenance(maintenanceEntity.getState_maintenance());
        dto.setId_member(maintenanceEntity.getId_member());
        dto.setId_location(maintenanceEntity.getId_location());
        return dto;
    }

    public MaintenanceDTO InsertarDatos(MaintenanceDTO data) {
        if (data == null || data.getState_maintenance() == null || data.getState_maintenance().isEmpty()) {
            throw new IllegalArgumentException("Mantenimiento y estado de  de mantenimiento no pueden ser nulos");
        }
        try {
            MaintenanceEntity entity = ConvertirAEntity(data);
            MaintenanceEntity mantenimientoguardado = repository.save(entity);
            return convertirADTO(mantenimientoguardado);
        } catch (Exception e) {
            log.error("Error al registrar el mantenimiento: " + e.getMessage());
            throw new ExceptionMaintenanceNotRegister("Error al registrar mantenimiento");
        }
    }

    private MaintenanceEntity ConvertirAEntity(MaintenanceDTO data) {
        MaintenanceEntity entity = new MaintenanceEntity();
        entity.setId_maintenance(data.getId_maintenance());
        entity.setDate_maintenance(data.getDate_maintenance());
        entity.setState_maintenance(data.getState_maintenance());
        entity.setId_member(data.getId_member());
        entity.setId_location(data.getId_location());
        return entity;
    }


    public MaintenanceDTO actualizarMantenimiento(Long id_maintenace, @Valid MaintenanceDTO json) {
        //varificar la existencia de usuario
        MaintenanceEntity existente = repository.findById(id_maintenace).orElseThrow(() -> new ExceptionMaintenanceNotFound("Mantenimiento no encontrado"));
        //convertir los datos dto a entity
        existente.setId_maintenance(json.getId_maintenance());
        existente.setDate_maintenance(json.getDate_maintenance());
        existente.setState_maintenance(json.getState_maintenance());
        existente.setId_member(json.getId_member());
        existente.setId_location(json.getId_location());
        //guardar los nuevos valores
        MaintenanceEntity mantrenimientoActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(mantrenimientoActualizado);
    }


    public boolean removerMantenimiento(Long id_maintenace) {
        try {
            MaintenanceEntity existente = repository.findById(id_maintenace).orElse(null);
            if (existente != null) {
                repository.deleteById(id_maintenace);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro mantenimineto con el id  " + id_maintenace + "para eliminar.", 1);

        }
    }


}
