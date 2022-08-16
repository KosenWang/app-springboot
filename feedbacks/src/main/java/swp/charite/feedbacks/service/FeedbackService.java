package swp.charite.feedbacks.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.feedbacks.dto.SendFeedbackDto;
import swp.charite.feedbacks.dto.GetFeedbackDto;
import swp.charite.feedbacks.model.Feedback;
import swp.charite.feedbacks.repository.FeedbackRepository;
import swp.charite.feedbacks.repository.GuidanceRepository;


@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private GuidanceRepository guidanceRepository;

    public Boolean create(SendFeedbackDto feedback) {
        if (guidanceRepository.existsById(feedback.getG_id())) {
            Feedback newFeedback = new Feedback(null, feedback.getG_id(), feedback.getFeedback(), new Date());
            feedbackRepository.save(newFeedback);
            return true;
        } else {
            return false;
        }
        
    }

    public List<GetFeedbackDto> query(Long g_id) {
    
        List<Feedback> fb_list = feedbackRepository.findByG_id(g_id);
        List<GetFeedbackDto> feedbacks = new ArrayList<GetFeedbackDto>();

        for (Feedback feedback : fb_list) {
            feedbacks.add(new GetFeedbackDto(feedback.getFeedback(), feedback.getDate()));
        }

        return feedbacks;
    }
  

}
