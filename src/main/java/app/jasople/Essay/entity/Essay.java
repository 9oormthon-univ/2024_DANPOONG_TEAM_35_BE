package app.jasople.Essay.entity;

import app.jasople.User.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
public class Essay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content; //자기소개서 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp createdDate;
    @Column(nullable = false)
    private boolean deleted = false;
    @Builder
    public Essay(Long id, String title, String content, User user, Timestamp createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
    }

}
