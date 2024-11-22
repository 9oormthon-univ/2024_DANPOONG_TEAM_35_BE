package app.jasople.User.service;

import app.jasople.User.converter.UserConverter;
import app.jasople.User.dto.KakaoUserInfoResponseDto;
import app.jasople.User.dto.UserDtoReq;
import app.jasople.User.dto.UserDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.entity.UserProfile;
import app.jasople.User.entity.enums.Type;
import app.jasople.User.repository.UserProfileRepository;
import app.jasople.User.repository.UserRepository;
import app.jasople.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User signup(UserDtoReq.SignUpReq signUpDto) {

        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword())) // 암호화
                .receiveAds(signUpDto.getReceiveAds())
                .build();
        //회원가입
        userRepository.save(user);
        //유저 프로필 추가
        UserProfile userProfile = UserProfile.createWithUser(user);
        userProfileRepository.save(userProfile);

        return user;
    }

    public UserDtoRes.UserLoginRes login(HttpServletRequest request, HttpServletResponse response, UserDtoReq.LoginReq loginDto) {

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(user.getType() == Type.KAKAO){
            throw new IllegalArgumentException("카카오 로그인 유저입니다.");
        }

        UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("해당 유저의 프로필이 없습니다."));

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        log.info("login refresh token : {}", refreshToken);

        // 쿠키 저장
        CookieUtil.deleteCookie(request, response, "refreshToken");
        CookieUtil.addCookie(response, "refreshToken", refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME_IN_COOKIE);

        return UserConverter.signInRes(user, accessToken,userProfile.getNickName());
    }


    public User kakaoSignup(KakaoUserInfoResponseDto userInfo) {

        User user = userRepository.findByEmail(userInfo.getKakaoAccount().getEmail())
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(userInfo.getKakaoAccount().getEmail())
                            .type(Type.KAKAO)
                            .build();
                    userRepository.save(newUser);
                    return newUser;
                });

        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .nickName(userInfo.getKakaoAccount().profile.getNickName())
                .build();
        userProfileRepository.save(userProfile);

        return user;
    }

    public UserDtoRes.UserLoginRes kakaoLogin(HttpServletRequest request, HttpServletResponse response, User user) {

        UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("해당 유저의 프로필이 없습니다."));

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        log.info("login refresh token : {}", refreshToken);

        // 쿠키 저장
        CookieUtil.deleteCookie(request, response, "refreshToken");
        CookieUtil.addCookie(response, "refreshToken", refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME_IN_COOKIE);

        return UserConverter.signInRes(user, accessToken, userProfile.getNickName());
    }
}
