package app.jasople.IndustryInfo.entity;

import app.jasople.Category.entity.Category;
import app.jasople.Keywords.entity.InfoKeywords;
import jakarta.persistence.*;
import lombok.Builder;
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

    private String title;

    private String content;

    @OneToMany(mappedBy = "industryInfo")
    private ScrapedInfo scrapedInfo;

    private String category;

    @Builder
    public IndustryInfo(String title, String content,String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }


}
