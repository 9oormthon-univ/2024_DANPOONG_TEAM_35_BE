package app.jasople.User.entity;

import app.jasople.Essay.entity.Essay;
import app.jasople.Experience.entity.Experience;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.Job.entity.InterestedJob;
import app.jasople.User.entity.enums.ReceiveAds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password; // 비밀번호

    @Column(unique = true)
    private String email; // 이메일

    @Column
    private ReceiveAds receiveAds; // 맞춤형 채용광고 수신 여부

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<InterestedJob> interestedJob = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Essay> essay = new ArrayList<>();;

    @OneToMany(mappedBy = "user")
    private List<Experience> experience = new ArrayList<>();;

    @OneToMany(mappedBy = "user")
    private List<ScrapedInfo> scrapedInfo = new ArrayList<>();;

    @Builder
    public User(String password, String email, ReceiveAds receiveAds) {
        this.email = email;
        this.password = password;
        this.receiveAds = receiveAds;
    }

    public User update(String password, String email, ReceiveAds receiveAds) {

        this.password = password;
        this.email = email;
        this.receiveAds = receiveAds;

        return this;
    }

    public void update(ReceiveAds receiveAds) {
        if (receiveAds != null) {
            this.receiveAds = receiveAds;
        }
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}