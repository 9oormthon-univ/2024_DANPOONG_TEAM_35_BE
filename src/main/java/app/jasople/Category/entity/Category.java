package app.jasople.Category.entity;

import app.jasople.Experience.entity.Experience;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // 양방향 관계 설정
    private List<Experience> experiences = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
    }

}
