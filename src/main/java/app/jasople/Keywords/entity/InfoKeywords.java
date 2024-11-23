package app.jasople.Keywords.entity;

import app.jasople.IndustryInfo.entity.IndustryInfo;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class InfoKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scrapedInfo_id")
    private ScrapedInfo scrapedInfo;

    @OneToOne
    @JoinColumn(name = "Keywords_id")
    private Keywords keyword;

}
