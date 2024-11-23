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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String company;

}
