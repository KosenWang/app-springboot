package swp.charite.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp.charite.backend.dto.DoctorDto;
import swp.charite.backend.dto.FeedbackDto;
import swp.charite.backend.dto.GetFeedbackDto;
import swp.charite.backend.dto.PatientDto;
import swp.charite.backend.services.interfaces.IDoctorCommandService;
import swp.charite.backend.services.interfaces.IPatientCommandService;

import java.util.Optional;

/**
 * REST-Controller for the doctor client
 */
@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    IDoctorCommandService doctorCommandService;

    @GetMapping(value = "/getSimpleString")
    @ResponseBody
    private ResponseEntity<String> getSimpleString() {
        return new ResponseEntity<>("This is the API of the patient client", HttpStatus.OK);
    }

    @PostMapping(value = "/createDoctor")
    @ResponseBody
    private ResponseEntity<Long> createDoctor(@RequestBody DoctorDto doctorDto) {
        Optional<Long> doctorId =  doctorCommandService.handleCreate(doctorDto);
        return doctorId.map(aLong -> new ResponseEntity<>(aLong, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping(value = "/getFeedback")
    @ResponseBody
    private ResponseEntity<FeedbackDto> getFeedback(@RequestParam Long doctor_id) {
        FeedbackDto feedbackDto = doctorCommandService.getFeedbackOfPatient(doctor_id);
        if (feedbackDto != null) {
            return new ResponseEntity<>(feedbackDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
