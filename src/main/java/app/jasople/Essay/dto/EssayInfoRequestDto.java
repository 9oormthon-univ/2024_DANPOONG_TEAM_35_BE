package app.jasople.Essay.dto;

import app.jasople.Job.entity.InterestedJob;
import app.jasople.Job.entity.Job;
import app.jasople.User.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EssayInfoRequestDto {

    @Builder
    public EssayInfoRequestDto(Long jobId, String company) {
        this.jobId = jobId;
        this.company = company;
    }

    private Long jobId;

    private String company;


    public InterestedJob toEntity(User user, Job job) {
        return InterestedJob.builder()
                .user(user)
                .job(job)
                .company(this.company)
                .build();
    }


}
