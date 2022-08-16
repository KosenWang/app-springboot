package swp.charite.diagnosis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import swp.charite.diagnosis.model.Diagnosis;
import swp.charite.diagnosis.service.DiagnosisService;


@RestController
public class DiagnosisController {

    @Autowired 
    private DiagnosisService diagnosisService;

    @PostMapping(value = "/diagnosis/create")
    public ResponseEntity<String> createDiagnosis (@RequestBody Diagnosis diagnosis){
        Boolean status = diagnosisService.create(diagnosis);
        if (status) {
            return new ResponseEntity<String>("Create diagnosis successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Diagnosis exists!", HttpStatus.BAD_REQUEST);
        }
    }
}
