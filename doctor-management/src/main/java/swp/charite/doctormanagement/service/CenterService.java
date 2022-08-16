package swp.charite.doctormanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.doctormanagement.dto.CenterDto;
import swp.charite.doctormanagement.model.Center;
import swp.charite.doctormanagement.repository.CenterRepository;


@Service
public class CenterService {
    @Autowired
    private CenterRepository centerRepository;

    public String addCenter(CenterDto center) {
        if (!centerRepository.existsByName(center.getName())) {
            Center newCenter = new Center(null, center.getName(), center.getLocation());
            centerRepository.save(newCenter);
            return "Create Center successfully!";
        } else {
            return "Center exists!";
        }
    }

    public Center queryCenter(CenterDto center) {
        if (!centerRepository.existsByName(center.getName())){
            return null;
        } else {
            Center newCenter = centerRepository.findByName(center.getName());
            return newCenter;
        }
    }

    public String deleteCenter(Long id) {   
        if (centerRepository.existsById(id)) {
            centerRepository.deleteById(id);
        return "Delete center successfully!";
        } else {
            return "Invalid Center ID.";
        }
    }
}
