package swp.charite.feedbacks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.charite.feedbacks.service.GuidanceCommandService;

@RestController
@RequestMapping(value = "/feedback")
public class GuidanceController {

    @Autowired
    GuidanceCommandService guidanceCommandService;

    @GetMapping("/guidance/done/{id}")
    public ResponseEntity<?> markGuidanceAsDone(@PathVariable Long id) {
        if (guidanceCommandService.markGuidance(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
