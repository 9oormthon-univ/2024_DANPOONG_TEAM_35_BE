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

    public Keywords(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
