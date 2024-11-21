package app.jasople.Experience.dto;

import app.jasople.Category.entity.Category;
import app.jasople.Experience.entity.Experience;
import app.jasople.Keywords.entity.Keywords;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ExperienceResponseDto {

    private Long id;
    private String title;
    private Long categoryId;
    private String background;

    private String solution;

    private String result;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<String> keywords;

    @Builder
    public ExperienceResponseDto(Experience experience, List<Keywords> keywords){
        this.id = experience.getId();
        this.title = experience.getTitle();
        this.categoryId = experience.getCategory().getId();
        this.background = experience.getBackground();
        this.solution = experience.getSolution();
        this.result = experience.getResult();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
        this.keywords = keywords.stream().map(Keywords::getName).collect(Collectors.toList());
    }
}
