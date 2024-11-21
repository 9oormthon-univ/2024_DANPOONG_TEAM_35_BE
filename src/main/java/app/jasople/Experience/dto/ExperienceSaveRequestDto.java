package app.jasople.Experience.dto;

import app.jasople.Category.entity.Category;
import app.jasople.Experience.entity.Experience;
import app.jasople.User.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ExperienceSaveRequestDto {
    public ExperienceSaveRequestDto(String title, Long categoryId, String background, String solution, String result, LocalDate startDate, LocalDate endDate, List<Long> keywordList) {
        this.title = title;
        this.categoryId = categoryId;
        this.background = background;
        this.solution = solution;
        this.result = result;
        this.startDate = startDate;
        this.endDate = endDate;
        this.keywordList = keywordList;
    }

    private String title;
    private Long categoryId;
    private String background;

    private String solution;

    private String result;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<Long> keywordList;

    public Experience ToEntity(Category category, User user){
        Experience newExperience = Experience.builder()
                .title(title)
                .category(category)
                .user(user)
                .background(background)
                .solution(solution)
                .result(result)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return newExperience;
    }
}
