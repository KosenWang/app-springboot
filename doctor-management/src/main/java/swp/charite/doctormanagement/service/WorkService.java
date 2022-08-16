package swp.charite.doctormanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swp.charite.doctormanagement.dto.WorkDto;
import swp.charite.doctormanagement.model.Work;
import swp.charite.doctormanagement.repository.WorkRepository;


@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;

    public String addWork(WorkDto work) {
        if (!workRepository.existsByD_idAndC_id(work.getD_id(), work.getC_id())) {
            Work newWork = new Work(null, work.getD_id(), work.getC_id());
            workRepository.save(newWork);
            return "Create Workrelation successfully!";
        } else {
            return "Workrelation exists!";
        }
    }

    public String deleteWork(WorkDto work) {   
        if (workRepository.existsByD_idAndC_id(work.getD_id(), work.getC_id())) {
            Work oldWork = workRepository.findByD_idAndC_id(work.getD_id(), work.getC_id());
            workRepository.deleteById(oldWork.getW_id());
        return "Center doctor successfully!";
        } else {
            return "No matching Entry";
        }
    }
}