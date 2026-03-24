package SSS0.Observation_Report.Service;

import SSS0.Observation_Report.Entities.Observation_ReportEntity;
import SSS0.Observation_Report.Exceptions.ExceptionObservationReportNoRegister;
import SSS0.Observation_Report.Exceptions.ExceptionObservationReportNotFound;
import SSS0.Observation_Report.Models.DTO.Observation_ReportDTO;
import SSS0.Observation_Report.Repository.Observation_ReportRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Observation_ReportService {

    @Autowired
    Observation_ReportRepository repository;

    public List<Observation_ReportDTO> ObtenerReportedeObservaciones() {
        List<Observation_ReportEntity> lista = repository.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private Observation_ReportDTO convertirADTO(Observation_ReportEntity observation_reportEntity) {
        //creando objeto a retornar
        Observation_ReportDTO dto = new Observation_ReportDTO();
        dto.setId_observation_report(observation_reportEntity.getId_observation_report());
        dto.setDate(observation_reportEntity.getDate());
        dto.setDescription(observation_reportEntity.getDescription());
        dto.setId_observation_employee(observation_reportEntity.getId_observation_employee());
        return dto;
    }


    public Observation_ReportDTO InsertarDatos(Observation_ReportDTO data) {
        if (data == null || data.getDate() == null || data.getDate() == null) {
            throw new IllegalArgumentException("Reporte de Observacion y fecha no pueden ser nulos");
        }
        try {
            Observation_ReportEntity entity = ConvertirAEntity(data);
            Observation_ReportEntity reportedeobservacionguardado = repository.save(entity);
            return convertirADTO(reportedeobservacionguardado);
        } catch (Exception e) {
            log.error("Error al registrar el reporte de observacion: " + e.getMessage());
            throw new ExceptionObservationReportNoRegister("Error al registrrar Reporte de observacion");
        }
    }


    private Observation_ReportEntity ConvertirAEntity(Observation_ReportDTO data) {
        Observation_ReportEntity entity = new Observation_ReportEntity();
        entity.setId_observation_report(data.getId_observation_report());
        entity.setDate(data.getDate());
        entity.setDescription(data.getDescription());
        entity.setId_observation_employee(data.getId_observation_employee());
        return entity;
    }


    public Observation_ReportDTO actualizarReportedeObservación(Long id_observation_report, @Valid Observation_ReportDTO json) {
        //varificar la existencia
        Observation_ReportEntity existente = repository.findById(id_observation_report).orElseThrow(() -> new ExceptionObservationReportNotFound("Reporte de Obsevacion no encontrado"));
        //convertir los datos dto a entity
        existente.setId_observation_report(json.getId_observation_report());
        existente.setDate(json.getDate());
        existente.setDescription(json.getDescription());
        existente.setId_observation_employee(json.getId_observation_employee());
        //guardar los nuevos valores
        Observation_ReportEntity reportedeObservacionActualizado = repository.save(existente);
        //convertir los datos de entity a DTO
        return convertirADTO(reportedeObservacionActualizado);
    }


    public boolean removerReportedeObservación(Long id_observation_report) {
        try {
            Observation_ReportEntity existente = repository.findById(id_observation_report).orElse(null);
            if (existente != null) {
                repository.deleteById(id_observation_report);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontro reporte de observacion con el id  " + id_observation_report + "para eliminar.", 1);

        }
    }

}
