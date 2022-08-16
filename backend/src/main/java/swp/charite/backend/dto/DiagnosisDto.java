package swp.charite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiagnosisDto {
    Long patient_id;
    Long doctor_id;
    String diagnosis;
}
