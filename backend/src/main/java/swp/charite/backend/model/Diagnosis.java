package swp.charite.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "diagnosis", schema = "prototyp_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    @Id
    @PositiveOrZero
    @Column(name = "dia_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dia_id;

    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "d_id", nullable = false)
    private Doctor doctor;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

}
