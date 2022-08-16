package swp.charite.feedbacks.model;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guidance", schema = "feedback_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guidance {

    @Id
    @Column(name = "g_id", nullable = false)
    private Long g_id;

    @Column(name = "guidance", nullable = false)
    private String guidance;

    @Column(name = "priority", nullable = false)
    @Enumerated
    private Priority priority;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "done", nullable = false)
    private boolean done;

}
