package SSS0.Observation.Repository;

import SSS0.Observation.Entities.ObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<ObservationEntity , Long> {
}
