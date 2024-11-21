package app.jasople.IndustryInfo.entity;

import app.jasople.Category.entity.Category;
import app.jasople.Keywords.entity.InfoKeywords;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class IndustryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "industryInfo")
    private ScrapedInfo scrapedInfo;

    @OneToOne(mappedBy = "industryInfo")
    private InfoKeywords infoKeywords;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;
}
