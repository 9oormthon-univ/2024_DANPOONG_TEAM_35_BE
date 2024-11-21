package app.jasople.User.entity;

import app.jasople.Essay.entity.Essay;
import app.jasople.Experience.entity.Experience;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.Job.entity.InterestedJob;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickName; // 닉네임

    @Column
    private String email; // 이메일

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<InterestedJob> interestedJob;

    @OneToMany(mappedBy = "user")
    private List<Essay> essay;

    @OneToMany(mappedBy = "user")
    private List<Experience> experience;

    @OneToMany(mappedBy = "user")
    private List<ScrapedInfo> scrapedInfo;
}
