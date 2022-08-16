package swp.charite.diagnosis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp.charite.diagnosis.dto.GuidanceFromDoctor;
import swp.charite.diagnosis.dto.GuidanceToPatient;
import swp.charite.diagnosis.dto.TypeDto;
import swp.charite.diagnosis.service.DiagnosisService;
import swp.charite.diagnosis.service.GuidanceService;

@RestController
@RequestMapping("/diagnosis")
public class GuidanceController {

    @Autowired
    private GuidanceService guidanceService;

    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping(value = "/guidance/create")
    public ResponseEntity<String> createGuidance(@RequestBody GuidanceFromDoctor guidance) {
        Boolean status = guidanceService.create(guidance);
        if (status) {
            return new ResponseEntity<String>("Create guidance successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Guidance exists!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/guidance/query/{p_id}")
    public ResponseEntity<GuidanceToPatient> queryGuidance(@PathVariable("p_id") Long p_id) {
        Long dia_id = diagnosisService.findByPatient(p_id);
        GuidanceToPatient guidance = guidanceService.query(dia_id);
        return new ResponseEntity<GuidanceToPatient>(guidance, HttpStatus.OK);
    }

    @PostMapping(value = "/advice")
    public ResponseEntity<?> getAdvice(@RequestBody TypeDto typeDto){
        String[] advice = guidanceService.getAdvice(typeDto.getType());
        if (advice != null) {
            return new ResponseEntity<String[]>(advice, HttpStatus.OK);
        } else {
            return new ResponseEntity<String[]>(new String[]{}, HttpStatus.NOT_FOUND);
        }
    }

}
