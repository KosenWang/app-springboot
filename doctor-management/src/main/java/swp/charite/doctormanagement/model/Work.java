package swp.charite.doctormanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "work", schema = "doctor_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Work {
    @Id
    @Column(name = "w_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long w_id;

    @Column(name = "d_id", nullable = false)
    private Long d_id;

    @Column(name = "c_id", nullable = false)
    private Long c_id;

}