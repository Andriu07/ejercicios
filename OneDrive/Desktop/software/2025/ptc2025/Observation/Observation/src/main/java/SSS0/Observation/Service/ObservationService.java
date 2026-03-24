package SSS0.Observation.Service;

import SSS0.Observation.Entities.ObservationEntity;
import SSS0.Observation.Exceptions.ExceptionObservationNotFound;
import SSS0.Observation.Exceptions.ExceptionObservationNotRegister;
import SSS0.Observation.Models.DTO.ObservationDTO;
import SSS0.Observation.Repository.ObservationRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ObservationService {

    @Autowired
    ObservationRepository repository;


    public List<ObservationDTO> ObtenerObservaciones() {
        List<ObservationEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ObservationDTO convertirADTO(ObservationEntity observationEntity) {
        //creando objeto a retornar
        ObservationDTO dto = new ObservationDTO();
        dto.setId_obervation(observationEntity.getId_observation());
        dto.setName_observation(observationEntity.getNameObservation());
        return dto;
    }

    public ObservationDTO InsertarDatos(ObservationDTO data) {
        if (data == null || data.getName_observation() == null || data.getName_observation().isEmpty()) {
            throw new IllegalArgumentException("Obsevacion y Nombre de Observacion no pueden ser nulos");
        }
        try {
            ObservationEntity entity = ConvertirAEntity(data);
            ObservationEntity observacionguardada = repository.save(entity);
            return convertirADTO(observacionguardada);
        } catch (Exception e) {
            log.error("Error al registrar la observacion: " + e.getMessage());
            throw new ExceptionObservationNotRegister("Error al registrrar observacion");
        }
    }


    private ObservationEntity ConvertirAEntity(ObservationDTO data) {
        ObservationEntity entity = new ObservationEntity();
        entity.setId_observation(data.getId_obervation());
        entity.setNameObservation(data.getName_observation());
        return entity;
    }


    public ObservationDTO actualizarObservacion(Long id_observation, @Valid ObservationDTO json) {
        //varificar la existencia
        ObservationEntity existente = repository.findById(id_observation).orElseThrow(() -> new ExceptionObservationNotFound("Observacion no encontrado"));
        //convertir los datos dto a entity
        existente.setId_observation(json.getId_obervation());
        existente.setNameObservation(json.getName_observation());
        //guardar los nuevos valores
        ObservationEntity observacionActualizada = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(observacionActualizada);
    }


    public boolean removerObservacion(Long id_observation) {
        try {
            ObservationEntity existente = repository.findById(id_observation).orElse(null);
            if (existente != null) {
                repository.deleteById(id_observation);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro la observacion con el id  " + id_observation + "para eliminar.", 1);

        }
    }

}