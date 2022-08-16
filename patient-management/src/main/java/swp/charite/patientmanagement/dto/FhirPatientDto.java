package swp.charite.patientmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FhirPatientDto {
    private String fhirID;
    private String firstname;
    private String surname;
    private String birthdate;
}
