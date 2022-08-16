package swp.charite.feedbacks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import swp.charite.feedbacks.dto.SendFeedbackDto;
import swp.charite.feedbacks.dto.GetFeedbackDto;
import swp.charite.feedbacks.service.FeedbackService;

@RestController
public class FeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/feedback/create")
    public ResponseEntity<String> createFeedback(@RequestBody SendFeedbackDto feedback) {
        Boolean status = feedbackService.create(feedback);
        if (status) {
            return new ResponseEntity<String>("Send feedback successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Invalid guidance ID!", HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping(value = "/feedback/query/{g_id}")
    public ResponseEntity<List<GetFeedbackDto>> query(@PathVariable("g_id") Long g_id){
        List<GetFeedbackDto> feedbacks = feedbackService.query(g_id);
        return new ResponseEntity<List<GetFeedbackDto>>(feedbacks, HttpStatus.OK);
    }

}
