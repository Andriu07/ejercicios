package MiApi._9.Repositories.Brigade;

import MiApi._9.Entities.BrigadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrigadeRepository extends JpaRepository<BrigadeEntity, Long> {
}
