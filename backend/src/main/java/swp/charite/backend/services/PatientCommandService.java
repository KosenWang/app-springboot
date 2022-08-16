package swp.charite.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.backend.dto.FeedbackDto;
import swp.charite.backend.dto.PatientDto;
import swp.charite.backend.model.Feedback;
import swp.charite.backend.model.Patient;
import swp.charite.backend.repository.DiagnosisRepository;
import swp.charite.backend.repository.FeedbackRepository;
import swp.charite.backend.repository.PatientRepository;
import swp.charite.backend.services.interfaces.IPatientCommandService;

import java.util.Optional;

@Service
public class PatientCommandService implements IPatientCommandService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Override
    public Optional<Long> handleCreate(PatientDto patientDto) {
        if (patientDto.getFirstname() == null || patientDto.getSurname() == null) {
            return Optional.empty();
        }
        Patient newPatient = new Patient(null, patientDto.getFirstname(), patientDto.getSurname());
        patientRepository.save(newPatient);
        return Optional.of(newPatient.getP_id());
    }

    @Override
    public Optional<Long> createFeedback(FeedbackDto feedbackDto) {
        if (feedbackDto.getPatient_id() == null || feedbackDto.getDiagnosis_id() == null) {
            return Optional.empty();
        }
        Feedback feedback = new Feedback(null, patientRepository.getById(feedbackDto.getPatient_id()),
                diagnosisRepository.getById(feedbackDto.getDiagnosis_id()), feedbackDto.getFeedback());
        feedbackRepository.save(feedback);
        return Optional.of(feedback.getF_id());
    }
}
