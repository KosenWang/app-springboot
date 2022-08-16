package swp.charite.diagnosis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp.charite.diagnosis.model.Priority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuidanceFromDoctor {
    private Long dia_id;
    private String guidance;
    private Priority priority;
}
