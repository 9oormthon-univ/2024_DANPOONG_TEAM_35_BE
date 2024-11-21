package app.jasople.security.handler;

import app.jasople.Config.code.status.ErrorStatus;
import app.jasople.security.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        log.error("exception : " + exception);

        /**
         * 토큰 없는 경우
         */
        if (exception == null) {
            log.info("[NULL TOKEN]");
            JwtAuthenticationFilter.setErrorResponse(response, ErrorStatus.JWT_EMPTY); // 토큰이 없음
            return;
        }

        else if ("로그아웃 되었습니다. 재 로그인하세요.".equals(exception)){
            log.info("[LOGOUT TOKEN]");
            JwtAuthenticationFilter.setErrorResponse(response, ErrorStatus.JWT_INVALID);
        }
    }
}
