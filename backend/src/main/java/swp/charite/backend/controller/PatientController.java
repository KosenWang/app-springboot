package swp.charite.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp.charite.backend.dto.FeedbackDto;
import swp.charite.backend.dto.PatientDto;
import swp.charite.backend.services.interfaces.IPatientCommandService;

import java.util.Optional;

/**
 * REST-Controller for the patient client
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    IPatientCommandService patientCommandService;

    @GetMapping(value = "/getSimpleString")
    @ResponseBody
    private ResponseEntity<String> getSimpleString() {
        return new ResponseEntity<>("This is the API of the patient client", HttpStatus.OK);
    }

    @PostMapping(value = "/createPatient")
    @ResponseBody
    private ResponseEntity<Long> createPatient(@RequestBody PatientDto patientDto) {
        Optional<Long> patientId =  patientCommandService.handleCreate(patientDto);
        return patientId.map(aLong -> new ResponseEntity<>(aLong, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping(value = "/createFeedback")
    @ResponseBody
    private ResponseEntity<Long> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        Optional<Long> feedbackId = patientCommandService.createFeedback(feedbackDto);
        return feedbackId.map(aLong -> new ResponseEntity<>(aLong, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
