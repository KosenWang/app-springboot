package swp.charite.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.backend.dto.DiagnosisDto;
import swp.charite.backend.model.Diagnosis;
import swp.charite.backend.repository.DiagnosisRepository;
import swp.charite.backend.repository.DoctorRepository;
import swp.charite.backend.repository.PatientRepository;
import swp.charite.backend.services.interfaces.IDiagnosisCommandService;

import java.util.Optional;

@Service
public class DiagnosisCommandService implements IDiagnosisCommandService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public Optional<Long> handleCreate(DiagnosisDto diagnosisDto) {
        if (diagnosisDto.getDiagnosis() == null || diagnosisDto.getPatient_id() == null || diagnosisDto.getDoctor_id() == null) {
            return Optional.empty();
        }
        Diagnosis newDiagnosis = new Diagnosis(null, patientRepository.getById(diagnosisDto.getPatient_id())
                , doctorRepository.getById(diagnosisDto.getDoctor_id()), diagnosisDto.getDiagnosis());
        diagnosisRepository.save(newDiagnosis);
        return Optional.of(newDiagnosis.getDia_id());
    }
}
