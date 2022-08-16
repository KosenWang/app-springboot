package swp.charite.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.charite.backend.model.Doctor;
import swp.charite.backend.model.Patient;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
