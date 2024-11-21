package app.jasople.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class UserDtoRes {

    @Data
    @AllArgsConstructor
    @Builder
    public static class UserLoginRes {
        private Long id;
        private String email;
        private String accessToken;
    }

}
