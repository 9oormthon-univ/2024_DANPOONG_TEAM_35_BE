package app.jasople.IndustryInfo.dto;

import app.jasople.IndustryInfo.entity.IndustryInfo;
import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.InfoKeywords;
import app.jasople.Keywords.entity.Keywords;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class IndustryInfoResponseDto {

    private Long id;

    private String title;
    private String content;

    private String category;


    @Builder
    public IndustryInfoResponseDto(IndustryInfo industryInfo) {
        this.id = industryInfo.getId();
        this.title = industryInfo.getTitle();
        this.content = industryInfo.getContent();
        this.category = industryInfo.getCategory();

    }
}
