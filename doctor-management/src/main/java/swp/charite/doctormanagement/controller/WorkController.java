package swp.charite.doctormanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swp.charite.doctormanagement.dto.WorkDto;
import swp.charite.doctormanagement.service.WorkService;


@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping(value = "/create")
    public String create(@RequestBody WorkDto work) {
        return workService.addWork(work);
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@RequestBody WorkDto work) {
        return workService.deleteWork(work);
    }
    
}