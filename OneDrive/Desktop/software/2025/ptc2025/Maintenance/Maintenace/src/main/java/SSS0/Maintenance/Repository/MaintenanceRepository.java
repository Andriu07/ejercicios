package SSS0.Maintenance.Repository;

import SSS0.Maintenance.Entities.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
}
