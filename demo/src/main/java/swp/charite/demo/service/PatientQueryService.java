package swp.charite.demo.service;

import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.demo.dto.PatientDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientQueryService {

    @Autowired
    private IParser parser;

    public List<PatientDto> mapFhirToPatientDto(List<IBaseResource> fhirPatients) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<PatientDto> patients = new ArrayList<>();
        if (fhirPatients.size() != 0) {
            for (IBaseResource iBr : fhirPatients) {
                String ser = parser.encodeResourceToString(iBr);
                Patient p = parser.parseResource(Patient.class, ser);
                PatientDto patientDto = new PatientDto(p.getNameFirstRep().getFamily(),
                        p.getNameFirstRep().getGiven().get(0).toString(), p.getBirthDate() == null ? null : dateFormat.format(p.getBirthDate()),
                        dateFormat.format(iBr.getMeta().getLastUpdated()));
                patients.add(patientDto);
            }
        }
        return patients;
    }

    //List<IBaseResource> patients = new ArrayList<>(BundleUtil.toListOfResources(fhirContext, bundle));
    //        String ser = parser.encodeResourceToString(bundle);
    //        Patient patient = parser.parseResource(Patient.class, ser);

}
