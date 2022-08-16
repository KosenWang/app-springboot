package swp.charite.backend.services.interfaces;

import swp.charite.backend.dto.DiagnosisDto;
import swp.charite.backend.dto.FeedbackDto;
import swp.charite.backend.dto.PatientDto;

import java.util.Optional;

public interface IPatientCommandService {

    Optional<Long> handleCreate(PatientDto patientDto);

    Optional<Long> createFeedback(FeedbackDto feedbackDto);
}
