package app.jasople.User.dto;

import app.jasople.User.entity.enums.ReceiveAds;
import lombok.*;

public class UserDtoReq {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpReq {
        String email;
        String password;
        ReceiveAds receiveAds; //맞춤형 채용광고 수신 여부
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginReq {
        String email;
        String password;
    }
}
