package app.jasople.IndustryInfo.dto;

import app.jasople.IndustryInfo.entity.IndustryInfo;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.Keywords.entity.InfoKeywords;
import app.jasople.User.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class IndustryInfoScrapDto {


    private Long industryInfoId;
    private Long userId; // 스크랩한 사용자의 ID
    private List<Long> keywordIds; // 키워드 ID 리스트

    @Builder
    public IndustryInfoScrapDto(Long industryInfoId, Long userId, List<Long> keywordIds) {
        this.industryInfoId = industryInfoId;
        this.userId = userId;
        this.keywordIds = keywordIds;
    }

    // ScrapedInfo 엔티티로 변환하는 메서드
    public ScrapedInfo toEntity(IndustryInfo industryInfo, List<InfoKeywords> infoKeywords, User user) {
        return ScrapedInfo.builder()
                .industryInfo(industryInfo)
                .user(user)
                .keywords(infoKeywords)
                .build();
    }

}
