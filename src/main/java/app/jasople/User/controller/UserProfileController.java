package app.jasople.User.controller;

import app.jasople.Config.ApiResponse;
import app.jasople.User.dto.UserDtoReq;
import app.jasople.User.dto.UserProfileDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.entity.UserProfile;
import app.jasople.User.service.UserProfileService;
import app.jasople.User.service.UserService;
import app.jasople.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userProfile")
@RequiredArgsConstructor
@Tag(name = "유저 프로필 api", description = "유저 프로필 Api입니다.")
public class UserProfileController {

    private static final Logger log = LoggerFactory.getLogger(UserProfile.class);
    private final UserProfileService userProfileService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/get")
    @Operation(summary = "유저 프로필 조회 API", description = "사용자의 프로필을 조회합니다.")
    public UserProfileDtoRes.UserProfileGet getProfile(HttpServletRequest request) {

        Long userId = jwtTokenProvider.getUserIdFromToken(request);

        log.debug("userId : {}", userId);
        return userProfileService.getProfile(userId);
    }


}
