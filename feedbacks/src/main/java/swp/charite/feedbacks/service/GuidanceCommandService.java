package swp.charite.feedbacks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.charite.feedbacks.dto.GuidanceCreateEventDto;
import swp.charite.feedbacks.dto.MarkGuidanceDto;
import swp.charite.feedbacks.model.Guidance;
import swp.charite.feedbacks.model.OutboxEntity;
import swp.charite.feedbacks.repository.GuidanceRepository;
import swp.charite.feedbacks.repository.OutboxRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class GuidanceCommandService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuidanceRepository guidanceRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public void handleAddedGuidance(JsonNode guidanceData) throws JsonProcessingException {
        GuidanceCreateEventDto guidanceCreateEventDto = mapper.treeToValue(mapper.readTree(guidanceData.asText()), GuidanceCreateEventDto.class);
        if (!guidanceRepository.existsById(guidanceCreateEventDto.getId())) {
            Guidance guidance = new Guidance(guidanceCreateEventDto.getId(), guidanceCreateEventDto.getGuidance(),
                    guidanceCreateEventDto.getPriority(), guidanceCreateEventDto.getDate(), guidanceCreateEventDto.isDone());
            guidanceRepository.save(guidance);
        }
    }

    public boolean markGuidance(Long id) {
        if (guidanceRepository.existsById(id)) {
            Guidance guidance = guidanceRepository.getReferenceById(id);
            guidance.setDone(true);
            
            MarkGuidanceDto markGuidanceDto = new MarkGuidanceDto(id, true);
            JsonNode jsonNode = mapper.convertValue(markGuidanceDto, JsonNode.class);
            OutboxEntity o = new OutboxEntity(UUID.randomUUID(), "guidance", guidance.getG_id().toString(), "guidance_done", jsonNode.toString());
            outboxRepository.save(o);
            outboxRepository.delete(o);
            
            return guidance.isDone();
        }
        return false;
    }

}
