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
@Table(name = "center", schema = "doctor_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Center {

    @Id
    @Column(name = "c_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long c_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

}
