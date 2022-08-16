package swp.charite.diagnosis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.diagnosis.dto.PatientCreateEventDto;
import swp.charite.diagnosis.dto.PatientDeleteEventDto;
import swp.charite.diagnosis.model.Patient;
import swp.charite.diagnosis.repository.PatientRepository;

import javax.transaction.Transactional;

@Service
public class PatientCommandService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    @Transactional
    public void handlePatientCreated(JsonNode patientData) throws JsonProcessingException {
        PatientCreateEventDto patientCreateEventDto = mapper.treeToValue(mapper.readTree(patientData.asText()), PatientCreateEventDto.class);
        if (!patientRepository.existsById(patientCreateEventDto.getId())) {
            Patient patient = new Patient(patientCreateEventDto.getId(), patientCreateEventDto.getFirstname(), patientCreateEventDto.getSurname(), patientCreateEventDto.getEmail());
            patientRepository.save(patient);
        }
    }

    @Transactional
    public void handlePatientDeleted(JsonNode patientData) throws JsonProcessingException {
        PatientDeleteEventDto patientDeleteEventDto = mapper.treeToValue(mapper.readTree(patientData.asText()), PatientDeleteEventDto.class);
        if (patientRepository.existsById(patientDeleteEventDto.getId())) {
            patientRepository.deleteById(patientDeleteEventDto.getId());
        }
    }

    public Patient query(Long p_id) {
        if (patientRepository.existsById(p_id)) {
            return patientRepository.findByPatientId(p_id);
        } else {
            return null;
        }
    }
}
