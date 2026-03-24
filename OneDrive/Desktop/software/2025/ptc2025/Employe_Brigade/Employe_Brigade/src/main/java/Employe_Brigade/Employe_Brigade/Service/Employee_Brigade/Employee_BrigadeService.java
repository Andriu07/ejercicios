package Employe_Brigade.Employe_Brigade.Service.Employee_Brigade;

import Employe_Brigade.Employe_Brigade.Entity.Employee_BrigadeEntity;
import Employe_Brigade.Employe_Brigade.Models.DTO.Employee_BrigadeDTO;
import Employe_Brigade.Employe_Brigade.Repositories.Employe_Brigade.Employee_BrigadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Employee_BrigadeService {

    @Autowired
    static Employee_BrigadeRepository repo;

    @Autowired
    private EntityManager entityManager;


    //metodo para obtner brigadas
    public List<Employee_BrigadeDTO> getAllEmployee_Brigade() {
        //obtnemos todos los empleados brigada "crudos" de la  base de datos
        List<Employee_BrigadeEntity> brigade = repo.findAll();
        //Transformamos cada empleado brigada "cruda " en una opcion mas limpia (DTO)
        return brigade.stream()
                .map(this::convertirAEmpleadoBrigadeDTO)
                .collect(Collectors.toList());

    }

    //METODO CONVERTIR A BRIGADA DTO
    private Employee_BrigadeDTO convertirAEmpleadoBrigadeDTO(Employee_BrigadeEntity employee_brigade) {
        Employee_BrigadeDTO dto = new Employee_BrigadeDTO();
        dto.setIdEmployee_Brigade(dto.getIdEmployee_Brigade());
        dto.setNit_Employee_Brigade(dto.getNit_Employee_Brigade());
        dto.setIdBrigade(dto.getIdBrigade());
        return dto;
    }


    @Transactional
    public Employee_BrigadeDTO InsertarEmpleadoBrigadaViaSP(Employee_BrigadeDTO Employee_BrigadeOFDTO) {
        if (Employee_BrigadeOFDTO == null || Employee_BrigadeOFDTO.getIdEmployee_Brigade() == null || Employee_BrigadeOFDTO.getIdEmployee_Brigade() == null ||
                Employee_BrigadeOFDTO.getNit_Employee_Brigade() == null || Employee_BrigadeOFDTO.getNit_Employee_Brigade().trim().isEmpty() ||
                Employee_BrigadeOFDTO.getIdBrigade() == null ) {
            throw new IllegalArgumentException("Empleado de Brigada, NIT de Empleado Brigada y Id Brigada no pueden ser nulos o vacíos.");
        }
        try {
            BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT seq_employee_brigade");
            //Convertir el BigDecimal a Long
            Long generatedId = sequenceValue.longValue();

            //asignar el ID generado al DTO
            Employee_BrigadeOFDTO.setIdEmployee_Brigade(generatedId);

            //crear y configurar la llamada del procedimiento almacenado
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("BrigadeEntity.insertEmployeeBrigadeByProcedure");

            query.setParameter("P_ID_EMPLOYEE_BRIGADE", Employee_BrigadeOFDTO.getIdEmployee_Brigade());
            query.setParameter("P_NIT_EMPLOYEE_BRIGADE", Employee_BrigadeOFDTO.getNit_Employee_Brigade());
            query.setParameter("P_ID_BRIGADE", Employee_BrigadeOFDTO.getIdBrigade());
            query.execute();
            return Employee_BrigadeOFDTO;
        } catch (Exception e) {
            System.err.println("Error al insertar Empleado brigada via SP" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al registrar  empleado brigada via procedimiento almacenado" + e);
        }
    }

    @Transactional
    public Employee_BrigadeDTO ActualizarEmpleadoBrigadaViaSP(Employee_BrigadeDTO employee_brigadeDTO, Long idEmployee_Brigade) {
        if (idEmployee_Brigade == null || employee_brigadeDTO == null || !idEmployee_Brigade.equals(employee_brigadeDTO.getIdEmployee_Brigade())) {
            throw new IllegalArgumentException("ID en la URL y en el cuerpo deban coincidir y no ser nulos");
        }
        if (employee_brigadeDTO.getIdEmployee_Brigade() == null || employee_brigadeDTO.getIdEmployee_Brigade() == null||
                employee_brigadeDTO.getNit_Employee_Brigade() == null || employee_brigadeDTO.getNit_Employee_Brigade().trim().isEmpty() || employee_brigadeDTO.getIdBrigade() == null) {
            throw new IllegalArgumentException("Empleado de Brigada, NIT de Empleado Brigada y Id Brigada no pueden ser nulos o vacíos");
        }
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("BrigadeEntity.updateEmployeeBrigadeByProcedure");

            query.setParameter("P_ID_EMPLOYE_BRIGADE", employee_brigadeDTO.getIdEmployee_Brigade());
            query.setParameter("P_NIT_EMPLOYEE_BRIGADE", employee_brigadeDTO.getNit_Employee_Brigade());
            query.setParameter("P_ID_BRIGADE", employee_brigadeDTO.getIdBrigade());

            query.execute();

            return employee_brigadeDTO;
        } catch (Exception e) {
            System.err.println("Error al insertar Empleado brigada via SP" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al registrar Empleadobrigada via procedimiento almacenado" + e);
        }
    }


    @Transactional
    public static void deleteEmployee_Brigade(Long idEmployeeBrigade) {
        if (!repo.existsById(idEmployeeBrigade)) {
            throw new RuntimeException("No se encontró brigada con ID: " + idEmployeeBrigade);
        }
        repo.deleteById(idEmployeeBrigade);
    }
}
