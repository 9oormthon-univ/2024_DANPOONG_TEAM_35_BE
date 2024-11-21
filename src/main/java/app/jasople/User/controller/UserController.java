package app.jasople.User.controller;

import app.jasople.Config.ApiResponse;
import app.jasople.User.dto.UserDtoReq;
import app.jasople.User.dto.UserDtoRes;
import app.jasople.User.entity.User;
import app.jasople.User.service.UserService;
import app.jasople.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "유저 api", description = "유저 Api입니다.")
public class UserController {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API", description = "사용자가 회원가입을 진행합니다.")
    public ApiResponse<String> signup(@RequestBody UserDtoReq.SignUpReq signUpDto) {
        User user = userService.signup(signUpDto);
        if (user == null) {
            return ApiResponse.onFailure("회원가입에 실패했습니다.", HttpStatus.BAD_REQUEST.toString(), null);
        }
        return ApiResponse.onSuccess(user.getEmail());
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "사용자가 로그인을 진행합니다.")
    public ApiResponse<UserDtoRes.UserLoginRes> login(@RequestBody UserDtoReq.LoginReq loginDto, HttpServletRequest request, HttpServletResponse response) {
//        User user = userService.login(loginDto);
//        if (user == null) {
//            return ApiResponse.onFailure("로그인에 실패했습니다.", HttpStatus.BAD_REQUEST.toString(), null);
//        }
//        return ApiResponse.onSuccess("로그인 성공");

        return ApiResponse.onSuccess(userService.login(request, response, loginDto));
    }





}
