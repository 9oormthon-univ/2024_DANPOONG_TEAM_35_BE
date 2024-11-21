package app.jasople.User.entity;

import app.jasople.User.entity.enums.Career;
import app.jasople.User.entity.enums.FinalEducation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String year; //출생 년도

    @Column
    private Career career; //경력 정보

    @Column
    private FinalEducation finalEducation; //최종 학력

    @Column
    private String major; //전공

    @Column
    private String univ; //학교

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
