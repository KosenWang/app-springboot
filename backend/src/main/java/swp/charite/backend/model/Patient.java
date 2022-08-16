package swp.charite.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "patient", schema = "prototyp_db", uniqueConstraints = @UniqueConstraint(columnNames = {"firstname", "surname"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @PositiveOrZero
    @Column(name = "p_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "surname", nullable = false)
    private String surname;

}
