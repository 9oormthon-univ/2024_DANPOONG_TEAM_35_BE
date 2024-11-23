package app.jasople.Keywords.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Keywords {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //키워드명

    @OneToMany(mappedBy = "keywords")
    private List<InfoKeywords> infoKeywords;

    @OneToMany(mappedBy = "keyword")
    private List<ExperienceKeywords> experienceKeywords;

    @Enumerated(EnumType.STRING)
    private keywordType keywordType;

    public Keywords(String name,keywordType keywordType) {
        this.name = name;
        this.keywordType = keywordType;
    }

}
