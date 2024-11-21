package app.jasople.User.dto;

import app.jasople.User.entity.enums.ReceiveAds;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserProfileDtoRes {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserProfileGet {
        private String email;
        private String nickName;
        private ReceiveAds receiveAds;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserProfileUpdate {
        private String nickName;
        private ReceiveAds receiveAds;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPasswordUpdate {
        private String password;
        private String newPassword;
    }
}