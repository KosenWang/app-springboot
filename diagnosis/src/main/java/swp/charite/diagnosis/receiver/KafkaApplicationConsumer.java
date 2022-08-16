package swp.charite.diagnosis.receiver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import swp.charite.diagnosis.service.DoctorCommandService;
import swp.charite.diagnosis.service.GuidanceCommandService;
import swp.charite.diagnosis.service.PatientCommandService;

import java.io.IOException;

@Component
public class KafkaApplicationConsumer {

    @Autowired ObjectMapper mapper;

    @Autowired
    PatientCommandService patientCommandService;

    @Autowired
    DoctorCommandService doctorCommandService;

    @Autowired
    GuidanceCommandService guidanceCommandService;

    @KafkaListener(topics = {"patient", "doctor", "guidance"})
    public void kafkaListener(@Payload byte[] message) throws IOException {
        JsonNode json = mapper.readTree(message);
        JsonNode payload = json.has("schema") ? json.get("payload") : json;

        if (!payload.has("eventType")) {
            return;
        }

        switch (payload.get("eventType").asText()) {
            case "patient_created":
                patientCommandService.handlePatientCreated(payload.get("payload"));
                break;
            case "patient_deleted":
                patientCommandService.handlePatientDeleted(payload.get("payload"));
                break;
            case "doctor_created":
                doctorCommandService.handleDoctorCreated(payload.get("payload"));
                break;
            case "doctor_deleted":
                doctorCommandService.handleDoctorDeleted(payload.get("payload"));
                break;
            case "guidance_done":
                guidanceCommandService.handleGuidanceDone(payload.get("payload"));
                break;
            default:
                break;
        }
    }
}
