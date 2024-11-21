package app.jasople.IndustryInfo.entity;

import app.jasople.Keywords.entity.InfoKeywords;
import app.jasople.User.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ScrapedInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "industryinfo_id")
    private IndustryInfo industryInfo;

    @OneToMany
    @JoinColumn(name = "keyword_id")
    private List<InfoKeywords> keywords;

    @Builder
    public ScrapedInfo(User user, IndustryInfo industryInfo, List<InfoKeywords> keywords) {
        this.user = user;
        this.industryInfo = industryInfo;
        this.keywords = keywords;
    }


}
