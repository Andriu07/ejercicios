package apiPractica.Practica.deRepasopERSONAL.Service;

import apiPractica.Practica.deRepasopERSONAL.Entity.ExpoEntity;
import apiPractica.Practica.deRepasopERSONAL.Exceptions.ExceptionMemberNotFound;
import apiPractica.Practica.deRepasopERSONAL.Exceptions.ExceptionMemberNotRegister;
import apiPractica.Practica.deRepasopERSONAL.Models.DTO.ExpoDTO;
import apiPractica.Practica.deRepasopERSONAL.Repository.ExpoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ExpoService {

    @Autowired
    ExpoRepository repository;

    public List<ExpoDTO> retornarMiembrosExpo() {
        List<ExpoEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ExpoDTO convertirADTO(ExpoEntity expoEntity) {
        //creando objeto a retornar
        ExpoDTO dto = new ExpoDTO();
        dto.setNombre(expoEntity.getNombre());
        dto.setApellido(expoEntity.getApellido());
        dto.setContrasena(expoEntity.getContrasena());
        dto.setId_rol(expoEntity.getId_rol());
        return dto;
    }

    public ExpoDTO InsertarDatos(ExpoDTO data) {
        if (data == null || data.getContrasena() == null || data.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("Miembro y contraeña no pueden ser nulos");
        }
        try {
            ExpoEntity entity = ConvertirAEntity(data);
            ExpoEntity miembroguardado = repository.save(entity);
            return convertirADTO(miembroguardado);
        } catch (Exception e) {
            log.error("Error al registrar el miembro: " + e.getMessage());
            throw new ExceptionMemberNotRegister("Error al registrar miembro");
        }
    }

    private ExpoEntity ConvertirAEntity(ExpoDTO data) {
        ExpoEntity entity = new ExpoEntity();
        entity.setId(data.getId());
        entity.setNombre(data.getNombre());
        entity.setContrasena(data.getContrasena());
        entity.setId_rol(data.getId_rol());
        return entity;
    }

    public ExpoDTO ActualizarMiembro(Long id, @Valid ExpoDTO json) {
        //varificar la existencia del miembro
        ExpoEntity existente = repository.findById(id).orElseThrow(() -> new ExceptionMemberNotFound("Empleado no encontrado"));
        //convertir los datos dto a entity
        existente.setNombre(json.getNombre());
        existente.setApellido(json.getApellido());
        existente.setContrasena(json.getContrasena());
        existente.setContrasena(json.getContrasena());
        //guardar los nuevos valores
        ExpoEntity miembroActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(existente);
    }

    public boolean removerMiembro(Long id) {
        try {
            ExpoEntity existente = repository.findById(id).orElse(null);
            if (existente != null) {
                repository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro miembro con el id  " + id + "para eliminar.", 1);

        }
    }


}