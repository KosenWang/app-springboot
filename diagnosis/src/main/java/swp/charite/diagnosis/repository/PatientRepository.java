package swp.charite.diagnosis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp.charite.diagnosis.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
    
    public Patient findByPatientId(Long p_id);
    
}
