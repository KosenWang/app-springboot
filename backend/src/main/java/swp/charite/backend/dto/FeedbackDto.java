package swp.charite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDto {
    Long patient_id;
    Long diagnosis_id;
    String feedback;
}
