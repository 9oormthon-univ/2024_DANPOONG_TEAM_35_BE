package app.jasople.User.controller;

import app.jasople.Config.ApiResponse;
import app.jasople.User.dto.KakaoUserInfoResponseDto;
import app.jasople.User.dto.UserDtoRes;
import app.jasople.User.service.KakaoService;
import app.jasople.User.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @GetMapping("/callback")
    public ApiResponse<UserDtoRes.UserLoginRes> callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        //회원가입, 로그인 동시진행
        return ApiResponse.onSuccess(userService.kakaoLogin(request,response, userService.kakaoSignup(userInfo)));
    }

    @GetMapping("/oauth/kakao")
    public ApiResponse<String> callback(@RequestParam("code") String code) {
        //회원가입, 로그인 동시진행
        return ApiResponse.onSuccess(code);
    }
}