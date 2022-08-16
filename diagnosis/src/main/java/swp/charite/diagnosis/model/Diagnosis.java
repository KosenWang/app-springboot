package swp.charite.diagnosis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diagnosis", schema = "diagnosis_db")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    @Column(name = "dia_id", nullable = false)
    private Long diagnosisId;

    @Column(name = "p_id", nullable = false)
    private Long patientId;

    @Column(name = "d_id", nullable = false)
    private Long doctorId;
}
