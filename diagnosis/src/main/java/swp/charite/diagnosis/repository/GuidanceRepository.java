package swp.charite.diagnosis.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.charite.diagnosis.model.Guidance;

@Repository
public interface GuidanceRepository extends JpaRepository<Guidance, Long> {

    public Boolean existsByDiagnosisId(Long dia_id);

    public Guidance findByDiagnosisId(Long dia_id); 

    public Guidance findByGuidanceId(Long g_id);

    @Query(value = "select * from diagnosis_db.guidance g where g.done=?1", nativeQuery = true)
    public List<Guidance> getUnfinishedGuidance(Boolean done);

}
