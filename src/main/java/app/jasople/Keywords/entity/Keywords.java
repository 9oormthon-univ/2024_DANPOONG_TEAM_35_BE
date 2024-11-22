package app.jasople.Keywords.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Keywords {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //키워드명

    @OneToOne(mappedBy = "keyword")
    private InfoKeywords infoKeywords;

    @OneToOne(mappedBy = "keyword")
    private ExperienceKeywords experienceKeywords;

    @Enumerated(EnumType.STRING)
    private keywordType keywordType;

    public Keywords(Long id, String name,keywordType keywordType) {
        this.id = id;
        this.name = name;
        this.keywordType = keywordType;
    }

}
