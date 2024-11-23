package app.jasople.Job.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 직무명

    @OneToMany(mappedBy = "job")
    private List<InterestedJob> interestedJobs;

    @Builder
    public Job(String name) {
        this.name = name;
    }

}
