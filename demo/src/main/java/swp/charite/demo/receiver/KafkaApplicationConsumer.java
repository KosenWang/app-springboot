package swp.charite.demo.receiver;

import java.io.IOException;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaApplicationConsumer {

    @Autowired ObjectMapper mapper;

    private static final Logger log = Logger.getLogger( KafkaApplicationConsumer.class.getName() );

    @KafkaListener(topics = {"patient"})
    public void kafkaListener(@Payload byte[] message) throws IOException {
        JsonNode json = mapper.readTree(message);
        JsonNode payload = json.has("schema") ? json.get("payload") : json;

        if (!payload.has("eventType")) {
            return;
        }

        switch (payload.get("eventType").asText()) {
            case "patient_created":
                log.info("Hello. Patient was created");
                break;
            default:
                break;
        }
    }
}
