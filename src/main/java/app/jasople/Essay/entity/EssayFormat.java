package app.jasople.Essay.entity;

import app.jasople.Experience.entity.Experience;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class EssayFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String question; // 질문

    @OneToOne
    @JoinColumn(name = "essay_id")
    private Essay essay;

    @OneToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;

}
