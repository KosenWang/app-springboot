package swp.charite.doctormanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.doctormanagement.dto.DoctorCreateEventDto;
import swp.charite.doctormanagement.dto.DoctorDeleteEventDto;
import swp.charite.doctormanagement.dto.DoctorDto;
import swp.charite.doctormanagement.model.Doctor;
import swp.charite.doctormanagement.model.OutboxEntity;
import swp.charite.doctormanagement.repository.DoctorRepository;
import swp.charite.doctormanagement.repository.OutboxRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    OutboxRepository outboxRepository;

    @Autowired
    ObjectMapper mapper;

    @Transactional
    public String addDoctor(DoctorDto doctor) {
        if (!doctorRepository.existsByFirstnameAndSurname(doctor.getFirstname(), doctor.getSurname())) {
            Doctor newDoctor = new Doctor(null, doctor.getFirstname(), doctor.getSurname(), doctor.getEmail());
            doctorRepository.save(newDoctor);

            DoctorCreateEventDto doctorCreateEventDto = new DoctorCreateEventDto(newDoctor.getD_id(), newDoctor.getFirstname(), newDoctor.getSurname());
            JsonNode jsonNode = mapper.convertValue(doctorCreateEventDto, JsonNode.class);
            OutboxEntity o = new OutboxEntity(UUID.randomUUID(), "doctor", newDoctor.getD_id().toString(), "doctor_created", jsonNode.toString());
            outboxRepository.save(o);
            outboxRepository.delete(o);

            return "Create Doctor successfully!";
        } else {
            return "Doctor exists!";
        }
    }

    public String updateEmail(DoctorDto doctor) {
        if (!doctorRepository.existsByFirstnameAndSurname(doctor.getFirstname(), doctor.getSurname())) {
            return "No doctor exists!";
        } else {
            Doctor oldDoctor = doctorRepository.findByFirstnameAndSurname(doctor.getFirstname(), doctor.getSurname());
            oldDoctor.setEmail(doctor.getEmail());
            doctorRepository.save(oldDoctor);
            return "Update email successfully!";
        }
    }


    public Doctor queryDoctor(DoctorDto doctor) {
        if (!doctorRepository.existsByFirstnameAndSurname(doctor.getFirstname(), doctor.getSurname())) {
            return null;
        } else {
            Doctor oldDoctor = doctorRepository.findByFirstnameAndSurname(doctor.getFirstname(), doctor.getSurname());
            return oldDoctor;
        }
    }

    @Transactional
    public String deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);

            DoctorDeleteEventDto doctorCreateEventDto = new DoctorDeleteEventDto(id);
            JsonNode jsonNode = mapper.convertValue(doctorCreateEventDto, JsonNode.class);
            OutboxEntity o = new OutboxEntity(UUID.randomUUID(), "doctor", doctorCreateEventDto.getId().toString(), "doctor_deleted", jsonNode.toString());
            outboxRepository.save(o);
            outboxRepository.delete(o);

            return "Delete doctor successfully!";
        } else {
            return "Invalid doctor ID.";
        }
    }
}
