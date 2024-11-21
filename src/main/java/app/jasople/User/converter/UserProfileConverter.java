package app.jasople.User.converter;


import app.jasople.User.dto.UserDtoRes;
import app.jasople.User.dto.UserProfileDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.entity.UserProfile;

public class UserProfileConverter {

    public static UserProfileDtoRes.UserProfileGet ProfileRes(UserProfile userProfile, User user) {
        return UserProfileDtoRes.UserProfileGet.builder()
                .email(user.getEmail())
                .receiveAds(user.getReceiveAds())
                .nickName(userProfile.getNickName())
                .build();

    }

}
