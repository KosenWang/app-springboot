package swp.charite.doctormanagement.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.doctormanagement.dto.DoctorDto;
import swp.charite.doctormanagement.model.Doctor;
import swp.charite.doctormanagement.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorQueryService {

    @Autowired
    private FhirContext fhirContext;

    @Autowired
    IParser parser;

    @Autowired
    private IGenericClient client;

    @Autowired
    private DoctorRepository doctorRepository;

    public int queryAllDoctor() {
        Bundle bundle = client
                .search()
                .forResource(Practitioner.class)
                .returnBundle(Bundle.class)
                .execute();
        List<Practitioner> practitionerList = BundleUtil.toListOfResourcesOfType(fhirContext, bundle, Practitioner.class);
        practitionerList = practitionerList.stream().filter(Practitioner::getActive).collect(Collectors.toList());
        for (Practitioner practitioner : practitionerList) {
            Doctor doctor = new Doctor(null, practitioner.getNameFirstRep().getGiven().size() > 0 ? practitioner.getNameFirstRep().getGiven().get(0).toString(): "",
                    practitioner.getNameFirstRep().getFamily() == null ? "" : practitioner.getNameFirstRep().getFamily(), "");
            doctorRepository.save(doctor);
        }
        return practitionerList.size();
    }

}
