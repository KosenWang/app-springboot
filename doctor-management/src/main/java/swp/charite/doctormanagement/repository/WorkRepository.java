package swp.charite.doctormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import swp.charite.doctormanagement.model.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{

    @Query(value = "SELECT EXISTS(SELECT * FROM doctor_db.work w WHERE w.d_id = ?1 AND w.c_id = ?2)"
            , nativeQuery = true)
    public boolean existsByD_idAndC_id(Long d_id, Long c_id);

    @Query(value = "SELECT * FROM doctor_db.work w WHERE w.d_id = ?1 AND w.c_id = ?2"
            , nativeQuery = true)
    public Work findByD_idAndC_id(Long d_id, Long c_id);
}
