package app.jasople.User.converter;


import app.jasople.User.dto.UserDtoRes;
import app.jasople.User.entity.User;

public class UserConverter {

    public static UserDtoRes.UserLoginRes signInRes(User user, String accessToken,String nickName) {
        return UserDtoRes.UserLoginRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .accessToken(accessToken)
                .nickName(nickName)
                .build();
    }

}
