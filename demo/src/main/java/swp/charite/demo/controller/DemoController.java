package swp.charite.demo.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import swp.charite.demo.service.PatientQueryService;

import java.util.*;

/**
 * Dummy controller to test the redirect of the gateway
 * All HTTP-requests with "/demo" are redirected to this controller
 */
@RequestMapping("/demo")
@Controller
public class DemoController {

    @Autowired
    private FhirContext fhirContext;

    @Autowired
    private IParser parser;

    @Autowired
    private IGenericClient client;

    @Autowired
    private PatientQueryService patientQueryService;

    /**
     * Dummy method to test the redirect of the gateway.
     * @return simple response message with "Hello World"
     */
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> returnSimpleDemo() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/fhir/{surname}/{firstname}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> handleFHIR(@PathVariable String surname, @PathVariable String firstname) {
        // Invoke the client
        Bundle bundle = client
                .search()
                .forResource(Patient.class)
                .where(Patient.FAMILY.contains().value(surname))
                .where(Patient.GIVEN.contains().value(firstname))
                .returnBundle(Bundle.class)
                .execute();
        /*List<IBaseResource> patients = new ArrayList<>(BundleUtil.toListOfResources(fhirContext, bundle));
        String ser = parser.encodeResourceToString(bundle);
        Patient patient = parser.parseResource(Patient.class, ser);*/
        return new ResponseEntity<>(patientQueryService.mapFhirToPatientDto(BundleUtil.toListOfResources(fhirContext, bundle)), HttpStatus.OK);
    }
}
