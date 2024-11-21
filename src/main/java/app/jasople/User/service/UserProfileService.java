package app.jasople.User.service;

import app.jasople.User.converter.UserProfileConverter;
import app.jasople.User.dto.UserDtoReq;
import app.jasople.User.dto.UserProfileDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.entity.UserProfile;
import app.jasople.User.repository.UserProfileRepository;
import app.jasople.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserProfileDtoRes.UserProfileGet getProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("해당 유저의 프로필이 없습니다."));

        return UserProfileConverter.ProfileRes(userProfile, user);


    }
}
