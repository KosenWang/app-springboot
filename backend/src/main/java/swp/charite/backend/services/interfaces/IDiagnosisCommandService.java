package swp.charite.backend.services.interfaces;

import swp.charite.backend.dto.DiagnosisDto;
import swp.charite.backend.dto.DoctorDto;

import java.util.Optional;

public interface IDiagnosisCommandService {

    Optional<Long> handleCreate(DiagnosisDto diagnosisDto);
}
