package app.jasople.Experience.converter;

import app.jasople.Experience.dto.ExseetUpdateDto;
import app.jasople.Experience.entity.Experience;
import app.jasople.Keywords.entity.ExperienceKeywords;
import app.jasople.Keywords.entity.Keywords;

public class ExperienceConverter {

    public static Experience toEntity(ExseetUpdateDto dto){
        return Experience.builder()
                .background(dto.getBackground())
                .solution(dto.getSolution())
                .result(dto.getResult())
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
