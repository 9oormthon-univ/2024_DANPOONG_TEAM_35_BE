package app.jasople.IndustryInfo.dto;

import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.Keywords;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class IndustryInfoScrapResponseDto {

    Long id;
    String title;
    String content;
    private List<KeywordResponseDto> keywords;

    @Builder
    public IndustryInfoScrapResponseDto(ScrapedInfo scrapedInfo, List<Keywords> keywords) {
        this.id = scrapedInfo.getId();
        this.title = scrapedInfo.getIndustryInfo().getTitle();
        this.content = scrapedInfo.getIndustryInfo().getContent();
        this.keywords = keywords.stream()
                .map(keyword -> new KeywordResponseDto(keyword))
                .collect(Collectors.toList());
    }
}
