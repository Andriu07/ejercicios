package SSS0.Observation_Category.Service;

import SSS0.Observation_Category.Entities.ObservationCategoryEntity;
import SSS0.Observation_Category.Excpetions.ExceptionObservationCategoryNotFound;
import SSS0.Observation_Category.Excpetions.ExceptionObservationCategoryNotRegister;
import SSS0.Observation_Category.Models.DTO.Observation_CategoryDTO;
import SSS0.Observation_Category.Repository.Observation_CategoryReporitory;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Observation_CategoryService {

    @Autowired
    Observation_CategoryReporitory repository;

    public List<Observation_CategoryDTO> ObtenerCategoriasdeObservacion() {
        List<ObservationCategoryEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    private Observation_CategoryDTO convertirADTO(ObservationCategoryEntity observation_categoryEntity) {
        //creando objeto a retornar
        Observation_CategoryDTO dto = new Observation_CategoryDTO();
       dto.setId_observation_category(observation_categoryEntity.getId_observation_category());
       dto.setNameObservationCategory(observation_categoryEntity.getNameObservationCategory());
       dto.setDescription(observation_categoryEntity.getDescription());
        return dto;
    }

    public Observation_CategoryDTO InsertarDatos(Observation_CategoryDTO data) {
        if (data == null || data.getNameObservationCategory() == null || data.getNameObservationCategory().isEmpty()) {
            throw new IllegalArgumentException("Categoria de observacion y nombre de categoria de observacion no pueden ser nulos");
        }
        try {
            ObservationCategoryEntity entity = ConvertirAEntity(data);
            ObservationCategoryEntity observationCategoryguardado = repository.save(entity);
            return convertirADTO(observationCategoryguardado);
        } catch (Exception e) {
            log.error("Error al registrar la categoria de observacion: " + e.getMessage());
            throw new ExceptionObservationCategoryNotRegister("Error al registrrar categoria de observacion");
        }
    }

    private ObservationCategoryEntity ConvertirAEntity(Observation_CategoryDTO data) {
        ObservationCategoryEntity entity = new ObservationCategoryEntity();
        entity.setId_observation_category(entity.getId_observation_category());
        entity.setNameObservationCategory(entity.getNameObservationCategory());
        entity.setDescription(entity.getDescription());
        return entity;
    }


    public Observation_CategoryDTO actualizarCategoriaObservacion(Long id_observation_category, @Valid Observation_CategoryDTO json) {
        //varificar la existencia
        ObservationCategoryEntity existente = repository.findById(id_observation_category).orElseThrow(() -> new ExceptionObservationCategoryNotFound("Categoria de observacion no encontrada"));
        //convertir los datos dto a entity
       existente.setId_observation_category(json.getId_observation_category());
       existente.setNameObservationCategory(json.getNameObservationCategory());
       existente.setDescription(json.getDescription());
        //guardar los nuevos valores
        ObservationCategoryEntity categoriadeObservacionActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(categoriadeObservacionActualizado);
    }


    public boolean removerCategoriadeObservacion(Long id_observation_category) {
        try {
            ObservationCategoryEntity existente = repository.findById(id_observation_category).orElse(null);
            if (existente != null) {
                repository.deleteById(id_observation_category);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro categoria de observacion con el id  " + id_observation_category + "para eliminar.", 1);

        }
    }
}
