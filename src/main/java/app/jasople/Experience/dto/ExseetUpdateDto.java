package app.jasople.Experience.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ExseetUpdateDto {

    private Long ExperienceId;
    private String title;
    private Long categoryId;
    private String background;
    private String solution;
    private String result;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> keywordList;
}
