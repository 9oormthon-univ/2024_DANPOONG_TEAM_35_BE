package app.jasople.Experience.entity;

import app.jasople.Category.entity.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

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

    @Builder
    public Experience(Category category,String title, String background, String solution, String result, Timestamp createdDate, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.background = background;
        this.solution = solution;
        this.result = result;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
    }


}
