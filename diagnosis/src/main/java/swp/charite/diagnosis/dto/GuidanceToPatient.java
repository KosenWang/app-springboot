package swp.charite.diagnosis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuidanceToPatient {
    private String guidance;
    private String priority;
    private Boolean done;
}
