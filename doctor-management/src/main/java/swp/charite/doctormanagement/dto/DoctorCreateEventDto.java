package swp.charite.doctormanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorCreateEventDto {
    private Long id;
    private String firstname;
    private String surname;
}
