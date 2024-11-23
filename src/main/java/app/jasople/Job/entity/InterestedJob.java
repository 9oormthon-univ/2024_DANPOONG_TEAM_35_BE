package app.jasople.Job.entity;

import app.jasople.User.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class InterestedJob {

    @Builder
    public InterestedJob(User user, Job job, String company) {
        this.user = user;
        this.job = job;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String company;

}
