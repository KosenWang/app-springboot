package swp.charite.feedbacks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp.charite.feedbacks.model.Guidance;

@Repository
public interface GuidanceRepository extends JpaRepository<Guidance, Long> {
    
}
