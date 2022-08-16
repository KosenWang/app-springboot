package swp.charite.diagnosis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.diagnosis.model.Diagnosis;
import swp.charite.diagnosis.repository.DiagnosisRepository;

@Service
public class DiagnosisService {
    
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Boolean create(Diagnosis diagnosis) {
        if (!diagnosisRepository.existsById(diagnosis.getDiagnosisId())){
            diagnosisRepository.save(diagnosis);
            return true;
        } else {
            return false;
        }
    }

    public Long findByPatient(Long p_id) {
        if (diagnosisRepository.existsByPatientId(p_id)) {
            Diagnosis diagnosis = diagnosisRepository.findByPatientId(p_id);
            return diagnosis.getDiagnosisId();
        } else {
            return null;
        }
    }

    public Long findByDoctor(Long d_id) {
        if (diagnosisRepository.existsByDoctorId(d_id)) {
            Diagnosis diagnosis = diagnosisRepository.findByDoctorId(d_id);
            return diagnosis.getDiagnosisId();
        } else {
            return null;
        }
    }

    public Diagnosis query(Long dia_id) {
        if (diagnosisRepository.existsById(dia_id)) {
            return diagnosisRepository.findByDiagnosisId(dia_id);
        } else {
            return null;
        }
    }

}
