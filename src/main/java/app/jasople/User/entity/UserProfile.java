package app.jasople.User.entity;

import app.jasople.User.dto.UserProfileDtoRes;
import app.jasople.User.entity.enums.Career;
import app.jasople.User.entity.enums.FinalEducation;
import jakarta.persistence.*;
import lombok.Builder;
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
    @Enumerated(EnumType.STRING)
    private Career career; //경력 정보

    @Column
    @Enumerated(EnumType.STRING)
    private FinalEducation finalEducation; //최종 학력

    @Column
    private String major; //전공

    @Column
    private String univ; //학교

    @Column
    private String nickName; //학교

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserProfile(User user, String year, Career career, FinalEducation finalEducation, String major, String univ, String nickName) {
        this.user = user;
        this.year = year;
        this.career = career;
        this.finalEducation = finalEducation;
        this.major = major;
        this.univ = univ;
        this.nickName = nickName;
    }

    public static UserProfile createWithUser(User user) {
        return UserProfile.builder()
                .user(user)
                .build();
    }

    public void updateEntity(UserProfileDtoRes.UserProfileUpdate dto) {
        if (dto.getNickName() != null) {
            this.nickName = dto.getNickName();
        }
    }
}
