package swp.charite.diagnosis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import swp.charite.diagnosis.dto.MarkGuidanceDto;
import swp.charite.diagnosis.model.Diagnosis;
import swp.charite.diagnosis.model.Guidance;
import swp.charite.diagnosis.model.Patient;
import swp.charite.diagnosis.repository.GuidanceRepository;
import swp.charite.diagnosis.util.MailService;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class GuidanceCommandService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuidanceRepository guidanceRepository;

    @Autowired
    private PatientCommandService patientService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private MailService mailService;

    @Transactional
    public void handleGuidanceDone(JsonNode guidanceData) throws JsonProcessingException {
        MarkGuidanceDto markGuidanceDto = mapper.treeToValue(guidanceData, MarkGuidanceDto.class);
        if (!guidanceRepository.existsById(markGuidanceDto.getId())) {
            Guidance guidance = guidanceRepository.getReferenceById(markGuidanceDto.getId());
            guidance.setDone(markGuidanceDto.isDone());
        }
    }

    // every week at 9 a.m. system will send email automaticly
    @Scheduled(cron = "0 0 9 ? * WED")
    public void sendGuidanceToPatient() {
        // find all unfinished guidances
        List<Guidance> guidances = guidanceRepository.getUnfinishedGuidance(false);
        
        if (guidances.size() == 0) {
            return;
        } else {
            Date today = new Date();
            for (Guidance guidance : guidances) {
                int diff = (int) (today.getTime() - guidance.getDate().getTime())/(1000 * 3600 * 24);
                // now only consider unfinished guidances inside one week
                if (diff < 7) {
                    Diagnosis diagnosis = diagnosisService.query(guidance.getDiagnosisId());
                    Patient patient = patientService.query(diagnosis.getPatientId());
                    mailService.sendEmail(patient, guidance);
                }
            }
        }

    }

}
