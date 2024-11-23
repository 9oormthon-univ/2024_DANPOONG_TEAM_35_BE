package app.jasople.Experience.entity;

import app.jasople.Category.entity.Category;
import app.jasople.Keywords.entity.ExperienceKeywords;
import app.jasople.User.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "experience")
    private List<ExperienceKeywords> experienceKeywords;

    @Column(nullable = false)
    private String title;
    @Lob
    @Column(name = "background", length = 200)
    private String background;

    @Lob
    @Column(name = "solution", length = 200)
    private String solution;

    @Lob
    @Column(name = "result", length = 200)
    private String result;

    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean deleted = false;

    public void update(String title, String background, String solution, String result, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.background = background;
        this.solution = solution;
        this.result = result;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Builder
    public Experience(Category category,String title, String background, String solution, String result, Timestamp createdDate, LocalDate startDate, LocalDate endDate, User user) {
        this.title = title;
        this.background = background;
        this.solution = solution;
        this.result = result;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.user = user;
    }


}
