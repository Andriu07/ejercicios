package apiPractica.Practica.deRepasopERSONAL.Repository;

import apiPractica.Practica.deRepasopERSONAL.Entity.ExpoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpoRepository extends JpaRepository<ExpoEntity, Long> {
}
