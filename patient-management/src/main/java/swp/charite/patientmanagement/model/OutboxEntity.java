package swp.charite.patientmanagement.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "outbox", schema = "patient_db")
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID uuid;

    @Column(name = "aggregate_type", nullable = false)
    private String aggregateType;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "payload", nullable = false)
    private String payload;
}
