package swp.charite.doctormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp.charite.doctormanagement.model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long>{
    public boolean existsByName(String name);
    public Center findByName(String name);
}