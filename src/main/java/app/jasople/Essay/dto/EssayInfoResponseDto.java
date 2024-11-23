package app.jasople.Essay.dto;

import app.jasople.Job.entity.InterestedJob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EssayInfoResponseDto {

    private String name;
    private String company;

    @Builder
    public EssayInfoResponseDto(InterestedJob interestedJob) {
        this.name = interestedJob.getJob().getName();
        this.company = interestedJob.getCompany();
    }
}
