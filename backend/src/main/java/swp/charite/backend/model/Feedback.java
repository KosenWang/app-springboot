package swp.charite.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "feedback", schema = "prototyp_db", uniqueConstraints = @UniqueConstraint(columnNames = {"p_id", "dia_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @PositiveOrZero
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long f_id;

    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dia_id", nullable = false)
    private Diagnosis diagnosis;

    @Column(name = "feedback", nullable = false)
    private String feedback;

}
