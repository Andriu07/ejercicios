package Department.SSSO.Repository;

import Department.SSSO.Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository  extends JpaRepository<DepartmentEntity, Long> {
}
