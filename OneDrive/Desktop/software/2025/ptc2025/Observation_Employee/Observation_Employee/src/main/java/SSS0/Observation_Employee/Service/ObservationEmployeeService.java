package SSS0.Observation_Employee.Service;

import SSS0.Observation_Employee.Entities.ObservationEmployeeEntity;
import SSS0.Observation_Employee.Excptions.ExceptionObservationEmployeeNotFound;
import SSS0.Observation_Employee.Excptions.ExceptionObservationEmployeeNotRegister;
import SSS0.Observation_Employee.Models.DTO.ObservationEmployeeDTO;
import SSS0.Observation_Employee.Repository.ObservationEmployeeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ObservationEmployeeService {

    @Autowired
    ObservationEmployeeRepository repository;

    public List<ObservationEmployeeDTO> ObtenerObaservacionesEmpleado() {
        List<ObservationEmployeeEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    private ObservationEmployeeDTO convertirADTO(ObservationEmployeeEntity observationEmployeeEntity) {
        //creando objeto a retornar
        ObservationEmployeeDTO dto = new ObservationEmployeeDTO();
        dto.setId_observation_employee(observationEmployeeEntity.getId_observation_employee());
        dto.setDt_report(observationEmployeeEntity.getDt_report());
        dto.setId_Observation(observationEmployeeEntity.getId_Observation());
        dto.setNit_employee(observationEmployeeEntity.getNit_employee());
        dto.setId_observation_category(observationEmployeeEntity.getId_observation_category());
        return dto;
    }

    public ObservationEmployeeDTO InsertarDatos(ObservationEmployeeDTO data) {
        if (data == null || data.getNit_employee() == null || data.getNit_employee().isEmpty()) {
            throw new IllegalArgumentException("Observacion empleado y Nit empleado no pueden ser nulos");
        }
        try {
            ObservationEmployeeEntity entity = ConvertirAEntity(data);
            ObservationEmployeeEntity observacionEmpleadoguardado = repository.save(entity);
            return convertirADTO(observacionEmpleadoguardado);
        } catch (Exception e) {
            log.error("Error al registrar observacion de empleado: " + e.getMessage());
            throw new ExceptionObservationEmployeeNotRegister("Error al registrar observacion empleado");
        }
    }

    private ObservationEmployeeEntity ConvertirAEntity(ObservationEmployeeDTO data) {
        ObservationEmployeeEntity entity = new ObservationEmployeeEntity();
       entity.setId_observation_employee(data.getId_observation_employee());
       entity.setDt_report(data.getDt_report());
       entity.setId_Observation(data.getId_Observation());
       entity.setNit_employee(data.getNit_employee());
       entity.setId_observation_category(data.getId_observation_category());
        return entity;
    }


    public ObservationEmployeeDTO actualizarObservacionEmpleado(Long id_observation_employee, @Valid ObservationEmployeeDTO json) {
        //varificar la existencia
        ObservationEmployeeEntity existente = repository.findById(id_observation_employee).orElseThrow(() -> new ExceptionObservationEmployeeNotFound("Observacion empleado no encontrada"));
        //convertir los datos dto a entity
        existente.setId_observation_employee(json.getId_observation_employee());
        existente.setDt_report(json.getDt_report());
        existente.setId_Observation(json.getId_Observation());
        existente.setNit_employee(json.getNit_employee());
        existente.setId_observation_category(json.getId_observation_category());
        //guardar los nuevos valores
        ObservationEmployeeEntity observationEmployeeActualizada = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(observationEmployeeActualizada);
    }


    public boolean removerObservacionEmpleado(Long id_observation_employee) {
        try {
            ObservationEmployeeEntity existente = repository.findById(id_observation_employee).orElse(null);
            if (existente != null) {
                repository.deleteById(id_observation_employee);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro observacion empleado con el id  " + id_observation_employee + "para eliminar.", 1);

        }
    }

}
