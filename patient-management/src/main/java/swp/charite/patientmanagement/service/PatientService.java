package swp.charite.patientmanagement.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.patientmanagement.dto.*;
import swp.charite.patientmanagement.model.OutboxEntity;
import swp.charite.patientmanagement.model.Patient;
import swp.charite.patientmanagement.repository.OutboxRepository;
import swp.charite.patientmanagement.repository.PatientRepository;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private FhirContext fhirContext;

    @Autowired
    private IParser parser;

    @Autowired
    private IGenericClient client;

    @Transactional
    public Boolean create(PatientDto patient) {
        if (!patientRepository.existsByFirstnameAndSurname(patient.getFirstname(), patient.getSurname())) {
            Patient newPatient = new Patient(null, patient.getFirstname(), patient.getSurname(), patient.getEmail());
            patientRepository.save(newPatient);

            PatientCreateEventDto patientCreateEventDto = new PatientCreateEventDto(newPatient.getPatientId(), newPatient.getFirstname(), newPatient.getSurname(), newPatient.getEmail());
            JsonNode jsonNode = mapper.convertValue(patientCreateEventDto, JsonNode.class);
            OutboxEntity o = new OutboxEntity(UUID.randomUUID(), "patient", newPatient.getPatientId().toString(), "patient_created", jsonNode.toString());
            outboxRepository.save(o);
            outboxRepository.delete(o);

            return true;
        } else {
            return false;
        }
    }

    public List<FhirPatientDto> getPatientsFromKIS(String firstname, String surname) {
        Bundle bundle = client
                .search()
                .forResource(org.hl7.fhir.r4.model.Patient.class)
                .where(org.hl7.fhir.r4.model.Patient.FAMILY.contains().value(surname))
                .where(org.hl7.fhir.r4.model.Patient.GIVEN.contains().value(firstname))
                .returnBundle(Bundle.class)
                .execute();

        List<IBaseResource> fhirPatients = BundleUtil.toListOfResources(fhirContext, bundle);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<FhirPatientDto> patients = new ArrayList<>();
        if (fhirPatients.size() != 0) {
            for (IBaseResource iBr : fhirPatients) {
                String ser = parser.encodeResourceToString(iBr);
                org.hl7.fhir.r4.model.Patient p = parser.parseResource(org.hl7.fhir.r4.model.Patient.class, ser);
                FhirPatientDto fhirPatientDto = new FhirPatientDto(p.getIdElement().getIdPart(), p.getNameFirstRep().getGiven().get(0).toString(),
                        p.getNameFirstRep().getFamily(), p.getBirthDate() == null ? null : dateFormat.format(p.getBirthDate()));
                patients.add(fhirPatientDto);
            }
        }
        return patients;
    }

    public PatientDto getPatientFromKisById(String id) {
         Bundle bundle = client
                 .search()
                 .forResource(org.hl7.fhir.r4.model.Patient.class)
                 .where(org.hl7.fhir.r4.model.Patient.RES_ID.exactly().code(id))
                 .returnBundle(Bundle.class)
                 .execute();

         org.hl7.fhir.r4.model.Patient p = parser.parseResource(org.hl7.fhir.r4.model.Patient.class,
                 parser.encodeResourceToString(bundle.getEntryFirstRep().getResource()));

         return new PatientDto(p.getNameFirstRep().getGiven().get(0).toString(), p.getNameFirstRep().getFamily(), "");
    }

    public Boolean update(PatientUpdateEmailDto patient) {
        if (!patientRepository.existsById(patient.getP_id())) {
            return false;
        } else {
            Patient oldPatient = patientRepository.findByPatientId(patient.getP_id());
            oldPatient.setEmail(patient.getEmail());
            patientRepository.save(oldPatient);
            return true;
        }
    }

    public PatientDto query(Long p_id) {
        if (!patientRepository.existsById(p_id)) {
            return null;
        } else {
            Patient oldPatient = patientRepository.findByPatientId(p_id);
            return new PatientDto(oldPatient.getFirstname(), oldPatient.getSurname(), oldPatient.getEmail());
        }
    }

    @Transactional
    public Boolean delete(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);

            PatientDeleteEventDto patientDeleteEventDto = new PatientDeleteEventDto(id);
            JsonNode jsonNode = mapper.convertValue(patientDeleteEventDto, JsonNode.class);
            OutboxEntity o = new OutboxEntity(UUID.randomUUID(), "patient", patientDeleteEventDto.getId().toString(), "patient_deleted", jsonNode.toString());
            outboxRepository.save(o);
            outboxRepository.delete(o);
            return true;
        } else {
            return false;
        }
    }
}
