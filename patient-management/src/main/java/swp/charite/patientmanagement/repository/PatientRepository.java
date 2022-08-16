package swp.charite.patientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp.charite.patientmanagement.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public boolean existsByFirstnameAndSurname(String firstname, String surname);

    public Patient findByFirstnameAndSurname(String firstname, String surname);

    public Patient findByPatientId(Long p_id);

}
