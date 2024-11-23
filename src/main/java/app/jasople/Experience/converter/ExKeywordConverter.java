package app.jasople.Experience.converter;

import app.jasople.Experience.entity.Experience;
import app.jasople.Keywords.entity.ExperienceKeywords;
import app.jasople.Keywords.entity.Keywords;

public class ExKeywordConverter {

    public static ExperienceKeywords toEntity(Experience ex, Keywords key){
        return ExperienceKeywords.builder()
                .experience(ex)
                .keyword(key)
                .build();
    }
}
