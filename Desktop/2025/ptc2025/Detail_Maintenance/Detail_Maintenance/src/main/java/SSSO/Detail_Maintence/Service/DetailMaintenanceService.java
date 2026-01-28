package SSSO.Detail_Maintence.Service;

import SSSO.Detail_Maintence.Entities.DetailMaintenanceEntity;
import SSSO.Detail_Maintence.Exceptions.ExceptionDetail_MaintenanceNotFound;
import SSSO.Detail_Maintence.Exceptions.ExceptionDteail_MaintenanceNotRegister;
import SSSO.Detail_Maintence.Models.DTO.DetailMaintenanceDTO;
import SSSO.Detail_Maintence.Repository.DetailMaintenanceRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DetailMaintenanceService {

    @Autowired
    DetailMaintenanceRepository repository;

    public List<DetailMaintenanceDTO> ObtenerDetallesMantenimiento() {
        List<DetailMaintenanceEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    private DetailMaintenanceDTO convertirADTO(DetailMaintenanceEntity detailMaintenanceEntity) {
        //creando objeto a retornar
        DetailMaintenanceDTO dto = new DetailMaintenanceDTO();
        dto.setId_detail_maintenance(detailMaintenanceEntity.getId_detail_maintenance());
        dto.setName_maintenance(detailMaintenanceEntity.getName_maintenance());
        dto.setTotal_time(detailMaintenanceEntity.getTotal_time());
        dto.setDescription(detailMaintenanceEntity.getDescription());
        dto.setActivity(detailMaintenanceEntity.getActivity());
        dto.setImg_maintenance(detailMaintenanceEntity.getImg_maintenance());
        dto.setId_assign_maintenance(detailMaintenanceEntity.getId_assign_maintenance());
        dto.setId_type_maintenance(detailMaintenanceEntity.getId_type_Maintenance());
        return dto;
    }

    public DetailMaintenanceDTO InsertarDatos(DetailMaintenanceDTO data) {
        if (data == null || data.getName_maintenance() == null || data.getName_maintenance().isEmpty()) {
            throw new IllegalArgumentException("Detalle mantenimineto y Nombre de mantenimineto no pueden ser nulos");
        }
        try {
            DetailMaintenanceEntity entity = ConvertirAEntity(data);
            DetailMaintenanceEntity usuarioguardado = repository.save(entity);
            return convertirADTO(usuarioguardado);
        } catch (Exception e) {
            log.error("Error al registrar el usuario: " + e.getMessage());
            throw new ExceptionDteail_MaintenanceNotRegister("Error al registrrar detalle mantenimiento");
        }
    }

    private DetailMaintenanceEntity ConvertirAEntity(DetailMaintenanceDTO data) {
        DetailMaintenanceEntity entity = new DetailMaintenanceEntity();
       entity.setId_detail_maintenance(data.getId_detail_maintenance());
       entity.setName_maintenance(data.getName_maintenance());
       entity.setTotal_time(data.getTotal_time());
       entity.setDescription(data.getDescription());
       entity.setActivity(data.getActivity());
       entity.setImg_maintenance(data.getImg_maintenance());
       entity.setId_assign_maintenance(data.getId_assign_maintenance());
       entity.setId_type_Maintenance(data.getId_type_maintenance());
        return entity;
    }


    public DetailMaintenanceDTO actualizarDetalleMantenimiento(Long id_detail_maintenance, @Valid DetailMaintenanceDTO json) {
        //varificar la existencia
        DetailMaintenanceEntity existente = repository.findById(id_detail_maintenance).orElseThrow(() -> new ExceptionDetail_MaintenanceNotFound("Detalle mantenimiento no encontrado"));
        //convertir los datos dto a entity
        existente.setId_detail_maintenance(json.getId_detail_maintenance());
        existente.setName_maintenance(json.getName_maintenance());
        existente.setTotal_time(json.getTotal_time());
        existente.setDescription(json.getDescription());
        existente.setActivity(json.getActivity());
        existente.setImg_maintenance(json.getImg_maintenance());
        existente.setId_assign_maintenance(json.getId_assign_maintenance());
        existente.setId_type_Maintenance(json.getId_type_maintenance());
        //guardar los nuevos valores
        DetailMaintenanceEntity detalleMantenimientoActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(detalleMantenimientoActualizado);
    }


    public boolean removerDetalleMantenimiento(Long id_detail_maintenance) {
        try {
            DetailMaintenanceEntity existente = repository.findById(id_detail_maintenance).orElse(null);
            if (existente != null) {
                repository.deleteById(id_detail_maintenance);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro detalle mantenimiento con el id_detail_maintenance  " + id_detail_maintenance + "para eliminar.", 1);

        }
    }

}
