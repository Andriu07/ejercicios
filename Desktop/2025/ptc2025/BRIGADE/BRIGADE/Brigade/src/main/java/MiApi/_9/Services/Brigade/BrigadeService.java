package MiApi._9.Services.Brigade;

import MiApi._9.Entities.BrigadeEntity;
import MiApi._9.Entities.BrigadeEntity;
import MiApi._9.Models.DTO.BrigadeDTO;
import MiApi._9.Models.DTO.BrigadeDTO;
import MiApi._9.Repositories.Brigade.BrigadeRepository;
import MiApi._9.Repositories.Brigade.BrigadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrigadeService {

    @Autowired
    BrigadeRepository repo;

    @Autowired
    private EntityManager entityManager;


    //metodo para obtner brigadas
    public List<BrigadeDTO> getAllBrigade() {
        //obtnemos todos las brigadas "crudas" de la  base de datos
        List<BrigadeEntity> brigade = repo.findAll();
        //Transformamos cada brigada "cruda " en una opcion mas limpia (DTO)
        return brigade.stream()
                .map(this::convertirABrigadeDTO)
                .collect(Collectors.toList());

    }

    //METODO CONVERTIR A BRIGADA DTO
    private BrigadeDTO convertirABrigadeDTO(BrigadeEntity brigade) {
        BrigadeDTO dto = new BrigadeDTO();
        dto.setId(brigade.getId());
        dto.setNameBrigade(brigade.getNameBrigade());
        dto.setCapacityStaff(brigade.getCapacityStaff());
        dto.setImgBrigade(brigade.getImgBrigade());
        dto.setDescription(brigade.getDescription());
        return dto;
    }


    @Transactional
    public BrigadeDTO InsertarBrigadaViaSP(BrigadeDTO BrigadeOFDTO){
        if (BrigadeOFDTO == null || BrigadeOFDTO.getNameBrigade() == null || BrigadeOFDTO.getNameBrigade().trim().isEmpty() ||
                BrigadeOFDTO.getCapacityStaff() == null || BrigadeOFDTO.getCapacityStaff() == null ||
                BrigadeOFDTO.getImgBrigade() == null || BrigadeOFDTO.getDescription() == null) {
            throw new IllegalArgumentException("Nombre de Brigada, Cpacidad de Staff, Imagen de Brigada y Decsripcion no pueden ser nulos o vacíos.");
    }
        try{
            BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT seq_brigade");
                    //Convertir el BigDecimal a Long
            Long generatedId = sequenceValue.longValue();

            //asignar el ID generado al DTO
            BrigadeOFDTO.setId(generatedId);

            //crear y configurar la llamada del procedimiento almacenado
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("BrigadeEntity.insertBrigadeByProcedure");

            query.setParameter("P_ID_Brigade", BrigadeOFDTO.getId());
            query.setParameter("P_Name_Brigade", BrigadeOFDTO.getNameBrigade());
            query.setParameter("P_Capacity_Staff", BrigadeOFDTO.getCapacityStaff());
            query.setParameter("P_Img_Brigade", BrigadeOFDTO.getImgBrigade());
            query.setParameter("P_Description", BrigadeOFDTO.getDescription());
            query.execute();
            return BrigadeOFDTO;
        }catch (Exception e){
            System.err.println("Error al insertar brigada via SP" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al registrar brigada via procedimiento almacenado" + e);
        }
    }

    @Transactional
    public BrigadeDTO ActualizarBrigadaViaSP(BrigadeDTO brigadeDTO, Long id){
        if (id == null || brigadeDTO == null || !id.equals(brigadeDTO.getId())){
            throw new IllegalArgumentException("ID en la URL y en el cuerpo deban coincidir y no ser nulos");
        }
        if(brigadeDTO.getNameBrigade() == null || brigadeDTO.getNameBrigade().trim().isEmpty()||
                brigadeDTO.getCapacityStaff() == null || brigadeDTO.getCapacityStaff() == null|| brigadeDTO.getId() == null ){
            throw new IllegalArgumentException("Nombre Brigada , Capacidad de Staff y  id  no pueden estar nulos o vacios");
        }
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("BrigadeEntity.updateBrigadeByProcedure");

            query.setParameter("P_ID_BRIGADE", brigadeDTO.getId());
            query.setParameter("P_NAME_BRIGADE", brigadeDTO.getNameBrigade());
            query.setParameter("P_CPACITY_STAFF", brigadeDTO.getCapacityStaff());

            query.execute();

            return brigadeDTO;
        }catch (Exception e){
            System.err.println("Error al insertar brigada via SP" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al registrar brigada via procedimiento almacenado" + e);
        }
    }


    @Transactional
    public static void deleteBrigade(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró brigada con ID: " + id);
        }
        repo.deleteById(id);
    }

}
