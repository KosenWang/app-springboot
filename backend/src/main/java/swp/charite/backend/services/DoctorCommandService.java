package swp.charite.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.backend.dto.DoctorDto;
import swp.charite.backend.dto.FeedbackDto;
import swp.charite.backend.dto.GetFeedbackDto;
import swp.charite.backend.model.Doctor;
import swp.charite.backend.model.Feedback;
import swp.charite.backend.repository.DoctorRepository;
import swp.charite.backend.repository.FeedbackRepository;
import swp.charite.backend.repository.PatientRepository;
import swp.charite.backend.services.interfaces.IDoctorCommandService;

import java.util.Optional;

@Service
public class DoctorCommandService implements IDoctorCommandService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Optional<Long> handleCreate(DoctorDto doctorDto) {
        if (doctorDto.getFirstname() == null || doctorDto.getSurname() == null) {
            return Optional.empty();
        }
        Doctor newDoctor = new Doctor(null, doctorDto.getFirstname(), doctorDto.getSurname());
        doctorRepository.save(newDoctor);
        return Optional.of(newDoctor.getD_id());
    }

    @Override
    public FeedbackDto getFeedbackOfPatient(Long doctor_id) {
        Optional<Feedback> op_feedback = feedbackRepository.getFeedbackByDoctorId(doctor_id);
        FeedbackDto feedbackDto = null;
        if (op_feedback.isPresent()) {
            feedbackDto = new FeedbackDto(op_feedback.get().getPatient().getP_id(), op_feedback.get().getDiagnosis().getDia_id(), op_feedback.get().getFeedback());
        }
        return feedbackDto;
    }
}
