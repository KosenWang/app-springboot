package swp.charite.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.charite.backend.model.Diagnosis;
import swp.charite.backend.model.Doctor;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
