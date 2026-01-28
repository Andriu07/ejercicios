package Department.SSSO.Service;

import Department.SSSO.Entities.DepartmentEntity;
import Department.SSSO.Exceptions.ExceptionDepartamentoNoEncontrado;
import Department.SSSO.Exceptions.ExceptionDepartamentoNotRegistrado;
import Department.SSSO.Models.DTO.DepartmentDTO;
import Department.SSSO.Repository.DepartmentRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentService {

    //inyectamoes el repository sobre service
    @Autowired
    DepartmentRepository repository;

    public List<DepartmentDTO> ObtenerDepartamentos() {
        List<DepartmentEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DepartmentDTO convertirADTO(DepartmentEntity departmentEntity) {
        //creando objeto a retornar
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId_department(departmentEntity.getId_department());
        dto.setNameDepartment(departmentEntity.getNameDepartment());
        dto.setDescriptionDepartment(departmentEntity.getDescriptionDepartment());
        dto.setLogoDepartment(departmentEntity.getLogoDepartment());
        return dto;
    }


    public DepartmentDTO InsertarDatos(DepartmentDTO data) {
        if (data == null || data.getNameDepartment() == null || data.getNameDepartment().isEmpty()) {
            throw new IllegalArgumentException("Departamento y Nombre de Departamento no pueden ser nulos");
        }
        try {
            DepartmentEntity entity = ConvertirAEntity(data);
            DepartmentEntity departamentoguardado = repository.save(entity);
            return convertirADTO(departamentoguardado);
        } catch (Exception e) {
            log.error("Error al registrar el usuario: " + e.getMessage());
            throw new ExceptionDepartamentoNotRegistrado("Error al registrrar departamento");
        }
    }

    private DepartmentEntity ConvertirAEntity(DepartmentDTO data) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId_department(entity.getId_department());
        entity.setNameDepartment(entity.getNameDepartment());
        entity.setDescriptionDepartment(entity.getDescriptionDepartment());
        entity.setLogoDepartment(entity.getLogoDepartment());
        return entity;
    }

    public DepartmentDTO actualizarDepartamento(Long id_department, @Valid DepartmentDTO json) {
        //varificar la existencia de usuario
        DepartmentEntity existente = repository.findById(id_department).orElseThrow(() -> new ExceptionDepartamentoNoEncontrado("Departamento no encontrado"));
        //convertir los datos dto a entity
        existente.setNameDepartment(json.getNameDepartment());
        existente.setDescriptionDepartment(json.getDescriptionDepartment());
        existente.setLogoDepartment(json.getLogoDepartment());
        //guardar los nuevos valores
        DepartmentEntity departamentoActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(departamentoActualizado);
    }


    public boolean removerDepartamento(Long id_department) {
        try {
            DepartmentEntity existente = repository.findById(id_department).orElse(null);
            if (existente != null) {
                repository.deleteById(id_department);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro usuario con el id  " + id_department + "para eliminar.", 1);

        }
    }


}
