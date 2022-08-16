package swp.charite.backend.services.interfaces;

import swp.charite.backend.dto.*;

import java.util.Optional;

public interface IDoctorCommandService {

    Optional<Long> handleCreate(DoctorDto doctorDto);

    FeedbackDto getFeedbackOfPatient(Long doctor_id);
}
