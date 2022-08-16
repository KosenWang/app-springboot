package swp.charite.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp.charite.backend.dto.DiagnosisDto;
import swp.charite.backend.services.interfaces.IDiagnosisCommandService;

import java.util.Optional;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    IDiagnosisCommandService diagnosisCommandService;

    @GetMapping(value = "/getSimpleString")
    @ResponseBody
    private ResponseEntity<String> getSimpleString() {
        return new ResponseEntity<>("This is the API of the patient client", HttpStatus.OK);
    }

    @PostMapping(value = "/createDiagnosis")
    @ResponseBody
    private ResponseEntity<Long> createDoctor(@RequestBody DiagnosisDto diagnosisDto) {
        Optional<Long> diagnosisId =  diagnosisCommandService.handleCreate(diagnosisDto);
        return diagnosisId.map(aLong -> new ResponseEntity<>(aLong, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
