package app.jasople.Config.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    //공통 예외
    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    BAD_REQUEST_VALIDATION(HttpStatus.BAD_REQUEST, "검증에 실패하였습니다."),

    //회원 예외
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "해당 요청은 로그인이 필요합니다."),

    NOT_FOUND_KEYWORD(HttpStatus.NOT_FOUND, "해당 키워드를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
