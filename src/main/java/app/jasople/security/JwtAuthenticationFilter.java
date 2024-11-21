package app.jasople.security;

import app.jasople.Config.ApiResponse;
import app.jasople.Config.code.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 요청 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveAccessToken(httpRequest);

        // 유효한 토큰인지 확인.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public static void setErrorResponse(HttpServletResponse response, ErrorStatus errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        ObjectMapper objectMapper = new ObjectMapper();

        ApiResponse<String> failureResponse = ApiResponse.onFailure(errorCode.getCode(),errorCode.getMessage(),null);
        String s = objectMapper.writeValueAsString(failureResponse);

        response.getWriter().write(s);
        ApiResponse.onFailure(errorCode.getCode(),errorCode.getMessage(),null);
    }
}
