package swp.charite.doctormanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swp.charite.doctormanagement.dto.CenterDto;
import swp.charite.doctormanagement.model.Center;
import swp.charite.doctormanagement.service.CenterService;


@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping(value = "/create")
    public String create(@RequestBody CenterDto center) {
        return centerService.addCenter(center);
    }

    @PostMapping(value = "/query")
    public Center query(@RequestBody CenterDto center) {
        return centerService.queryCenter(center);
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return centerService.deleteCenter(id);
    }
    
}

