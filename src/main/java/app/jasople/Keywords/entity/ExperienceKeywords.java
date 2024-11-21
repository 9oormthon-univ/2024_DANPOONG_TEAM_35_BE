package app.jasople.Keywords.entity;

import app.jasople.Experience.entity.Experience;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ExperienceKeywords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    private Experience experience;

    @ManyToOne
    @JoinColumn(name = "keyword_id",nullable = false)
    private Keywords keyword;

    @Builder
    public ExperienceKeywords(Experience experience, Keywords keyword) {
        this.experience = experience;
        this.keyword = keyword;
    }



}
