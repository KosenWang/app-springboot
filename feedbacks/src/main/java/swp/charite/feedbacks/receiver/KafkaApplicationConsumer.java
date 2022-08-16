package swp.charite.feedbacks.receiver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import swp.charite.feedbacks.service.GuidanceCommandService;

import java.io.IOException;

@Component
public class KafkaApplicationConsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuidanceCommandService guidanceCommandService;

    @KafkaListener(topics = {"guidance"})
    public void kafkaListener(@Payload byte[] message) throws IOException {
        JsonNode json = mapper.readTree(message);
        JsonNode payload = json.has("schema") ? json.get("payload") : json;

        if (!payload.has("eventType")) {
            return;
        }

        switch (payload.get("eventType").asText()) {
            case "guidance_added":
                guidanceCommandService.handleAddedGuidance(payload.get("payload"));
                break;
            default:
                break;
        }
    }
}
