package app.jasople.User.service;

import app.jasople.Config.ApiResponse;
import app.jasople.User.converter.UserProfileConverter;
import app.jasople.User.dto.UserProfileDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.entity.UserProfile;
import app.jasople.User.entity.enums.Type;
import app.jasople.User.repository.UserProfileRepository;
import app.jasople.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;


    public UserProfileDtoRes.UserProfileGet getProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("해당 유저의 프로필이 없습니다."));

        return UserProfileConverter.ProfileRes(userProfile, user);
    }

    public ApiResponse<String> updateProfile(Long userId, UserProfileDtoRes.UserProfileUpdate dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("해당 유저의 프로필이 없습니다."));

        userProfile.updateEntity(dto);
        user.update(dto.getReceiveAds());

        log.info(user.getEmail());
        log.info(userProfile.getNickName());

        userRepository.save(user);
        userProfileRepository.save(userProfile);

        return ApiResponse.onSuccess("성공입니다");
    }

    public ApiResponse<String> updatePassword(Long userId, UserProfileDtoRes.UserPasswordUpdate userPasswordUpdate) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        if(user.getType() == Type.KAKAO){
            throw new IllegalArgumentException("카카오 로그인 유저입니다.");
        }

        if (!passwordEncoder.matches(userPasswordUpdate.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        user.updatePassword(passwordEncoder.encode(userPasswordUpdate.getNewPassword()));
        userRepository.save(user);

        return ApiResponse.onSuccess("성공입니다");
    }

    public ApiResponse<String> deleteProfile(Long userId, UserProfileDtoRes.UserProfileDelete userProfileDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        if(user.getType() == Type.KAKAO){
            userRepository.delete(user);

            return ApiResponse.onSuccess("성공입니다");
        }

        if (!passwordEncoder.matches(userProfileDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);

        return ApiResponse.onSuccess("성공입니다");
    }
}
