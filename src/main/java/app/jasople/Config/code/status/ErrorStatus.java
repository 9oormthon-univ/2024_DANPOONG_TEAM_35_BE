package app.jasople.Config.code.status;

import app.jasople.Config.code.BaseErrorCode;
import app.jasople.Config.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    USER_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "USER4002", "사용자는 활성 상태가 아닙니다."),
    USER_NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "USER4003", "닉네임이 이미 존재합니다."),
    USER_ACCOUNT_DUPLICATED(HttpStatus.BAD_REQUEST, "USER4004", "이미 등록된 아이디입니다."),
    USER_PASSWORD_NOT_EQUAL(HttpStatus.BAD_REQUEST, "USER4005", "사용자의 비밀번호와 일치하지 않습니다."),
    USER_ALREADY_DELETED(HttpStatus.NOT_FOUND, "USER4006", "이미 삭제된 사용자입니다."),
    USER_ALREADY_WITHDRAW(HttpStatus.BAD_REQUEST, "USER4007", "이미 탈퇴 요청한 사용자입니다."),
    USER_ACCOUNT_NOT_MATCHED(HttpStatus.BAD_REQUEST,"USER4008", "전화번호로 찾은 계정의 아이디와 일치하지 않습니다."),
    USER_NAME_NOT_MATCHED(HttpStatus.BAD_REQUEST, "USER4009", "전화번호로 찾은 계정의 사용자 이름과 일치하지 않습니다."),
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "USER4010", "비밀번호 비밀번호 확인이 맞지 않습니다."),
    USER_ACCOUNT_IS_YOURS(HttpStatus.BAD_REQUEST, "USER4011", "본인이 사용중인 아이디입니다."),
    USER_NICKNAME_IS_YOURS(HttpStatus.BAD_REQUEST, "USER4012", "본인이 사용중인 닉네임입니다."),
    USER_PHONE_IS_YOURS(HttpStatus.BAD_REQUEST, "USER4013", "본인이 사용중인 휴대폰번호입니다."),
    USER_PHONE_IS_USED(HttpStatus.BAD_REQUEST, "USER4014", "이미 사용중인 휴대폰번호입니다. 관리자에게 문의하세요"),
    USER_IS_SUSPENSION(HttpStatus.BAD_REQUEST, "USER4015", "정지된 계정입니다."),
    USER_IS_ALREADY_REGISTERED_KAKAO(HttpStatus.IM_USED, "USER4016", "이미 카카오계쩡으로 가입된 전화번호입니다. 카카오로 로그인해주세요."),
    USER_IS_INTEGRATED_KAKAO(HttpStatus.ACCEPTED, "USER4017", "사용자의 계정이 카카오계정과 통합되었습니다. YESOL 계정 혹은 카카오로 로그인해주세요."),


    // 관리자 관련 에러
    NOT_ADMIN(HttpStatus.UNAUTHORIZED, "ADMIN4001", "관리자의 권한이 없습니다."),


    // 제재 관련 에러
    PROHIBIT_NOT_FOUND(HttpStatus.NOT_FOUND, "PROHIBIT4001", "해당하는 유저의 제재가 없습니다."),


    // 인증 관련 에러
    USER_NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH4001", "휴대폰 인증이 필요합니다."),


    // 전공 관련 에러
    MAJORS_NOR_FOUND(HttpStatus.NOT_FOUND, "MAJORS4001", "해당하는 전공이 없습니다."),
    USER_MAJOR_IS_YOURS(HttpStatus.BAD_REQUEST, "MAJORS4002", "아마 사용자의 전공입니다."),

    // 토큰 관련 에러
    JWT_AUTHORIZATION_FAILED(HttpStatus.UNAUTHORIZED, "JWT4001", "권한이 없습니다."),
    JWT_INVALID(HttpStatus.UNAUTHORIZED, "JWT4002", "유효하지 않은 토큰입니다."),
    JWT_EMPTY(HttpStatus.UNAUTHORIZED, "JWT4003", "JWT 토큰을 넣어주세요."),
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT4004", "만료된 토큰입니다."),
    JWT_REFRESHTOKEN_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "JWT4005", "RefreshToken이 일치하지 않습니다."),


    // 게시판 관련 에러
    BOARDLIKE_DUPLICATED(HttpStatus.BAD_REQUEST, "BOARD4001", "이미 좋아요 되었습니다."),
    BOARDLIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD4002", "이미 좋아요가 취소되었습니다. "),
    BOARD_ALREADY_DELETED(HttpStatus.NOT_FOUND, "BOARD4003", "이미 게시글이 삭제되었습니다."),
    BOARD_NOT_SEARCH(HttpStatus.NOT_FOUND, "BOARD4004", "찾는 게시판이 없습니다."),
    BOARD_SEARCHTYPE_NOT_FOUND(HttpStatus.BAD_REQUEST, "BOARD4005", "검색 조건이 잘못되었습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD4006", "게시글을 찾지 못했습니다."),
    BOARD_USER_NOT_MATCH(HttpStatus.UNAUTHORIZED, "BOARD4007", "본인의 게시글이 아닙니다."),
    BOARD_CANNOT_DELETE(HttpStatus.BAD_REQUEST, "BOARD4008", "삭제할 수 없는 게시글입니다."),
    BOARD_CANNOT_UPDATE(HttpStatus.BAD_REQUEST, "BOARD4009", "수정할 수 없는 게시글입니다."),
    BOARD_FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "BOARD4010", "게시판 사진 업로드 실패입니다."),
    BOARD_NOT_COMPLAINT(HttpStatus.BAD_REQUEST, "BOARD4011", "자신의 게시글은 신고할 수 없습니다."),
    BOARD_NOT_LIKE(HttpStatus.BAD_REQUEST, "BOARD4012", "자신의 게시글은 좋아요 할 수 없습니다."),
    BOARD_PICTURE_OVERED(HttpStatus.BAD_REQUEST, "BOARD4013", "사진 개수를 초과하였습니다."),

    // 자주 묻는 질문 관련 에러
    FAQ_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FAQ4001", "카테고리 값이 잘못 되었습니다."),
    FAQ_NOT_FOUND(HttpStatus.NOT_FOUND, "FAQ4002", "자주 묻는 질문을 찾을 수 없습니다."),

    // 댓글 관련 에러
    PIN_NOT_REGISTER(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4001", "댓글 등록에 실패했습니다."),
    PIN_NOT_FOUND(HttpStatus.NOT_FOUND, "PIN4002", "댓글을 찾을 수 없습니다."),
    PIN_NOT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4003", "댓글을 삭제하는데 실패하였습니다."),
    PIN_NOT_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4005", "댓글을 수정하는데 실패하였습니다."),
    PIN_NOT_REPORT(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4006", "댓글을 신고하는데 실패하였습니다."),
    PIN_NOT_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4007", "댓글 좋아요 등록에 실패하였습니다."),
    PIN_NOT_UNLIKE(HttpStatus.INTERNAL_SERVER_ERROR, "PIN4008", "댓글 좋아요 취소하기를 실패하였습니다."),
    PIN_NOT_NOTIFICATION(HttpStatus.BAD_REQUEST, "PIN4009", "댓글 알림 전송 실패하였습니다."),
    PIN_PICTURE_OVERED(HttpStatus.BAD_REQUEST, "PIN4013", "사진 개수를 초과하였습니다."),


    // 대댓글 관련 에러
    COMMENT_NOT_REGISTER(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4001", "대댓글 등록에 실패했습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT4002", "대댓글을 찾을 수 없습니다."),
    COMMENT_NOT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4003", "대댓글을 삭제하는데 실패하였습니다."),
    COMMENT_NOT_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4005", "대댓글을 수정하는데 실패하였습니다."),
    COMMENT_NOT_REPORT(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4006", "대댓글을 신고하는데 실패하였습니다."),
    COMMENT_NOT_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4007", "대댓글 좋아요 등록에 실패하였습니다."),
    COMMENT_NOT_UNLIKE(HttpStatus.INTERNAL_SERVER_ERROR, "COMMENT4008", "대댓글 좋아요 취소하기를 실패하였습니다."),
    COMMENT_PICTURE_OVERED(HttpStatus.BAD_REQUEST, "COMMENT4013", "사진 개수를 초과하였습니다."),


    // 예제 관련 에러
    FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND, "FAVORITE4001", "즐겨찾기 예제를 찾는것에 실패하였습니다."),
    FAVORITE_NOT_REGISTER(HttpStatus.INTERNAL_SERVER_ERROR, "FAVORITE4003", "즐겨찾기 등록에 실패했습니다."),
    EXAMPLE_NOT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "EXAMPLE4002", "예제 삭제에 실패했습니다."),
    EXAMPLE_NOT_FOUND(HttpStatus.NOT_FOUND, "EXAMPLE4004" , "예제를 찾을 수 없습니다."),


    // 알람 관련 에러
    ALARM_SET_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_SET4001", "알람셋이 없습니다! 관리자에게 문의하세요!"),

    ALARM_READ_NOT_FOUND(HttpStatus.BAD_REQUEST, "ALARM4001", "알림 읽음 유무 값이 잘 못 되었습니다."),
    ALARM_NOT_FOUND(HttpStatus.BAD_REQUEST, "ALARM4002", "알림을 찾을 수 없습니다."),
    ALARM_NOT_MATCH(HttpStatus.BAD_REQUEST, "ALARM4003", "자신의 알림이 아닙니다."),

    // 사진 관련 헤어
    PROFILE_IS_DEFAULT(HttpStatus.BAD_REQUEST, "PIC4001", "사진이 이미 기본값입니다."),


    // 내부 서버 에러
    USER_FILE_CHANGE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR5004", "파일 전환에 실패하였습니다."),

    //문의 관련 에러
    QNA_FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "QNA4001", "문의 사진 업로드 실패입니다."),
    QNA_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA4002", "문의사항을 찾을 수 없습니다"),
    QNA_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA4003", "문의사항 상태가 잘 못 되었습니다."),
    QNA_PICTURE_OVERED(HttpStatus.BAD_REQUEST, "BOARD4013", "사진 개수를 초과하였습니다."),

    //게시물 신고 에러
    BOARDCOMPLAINT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMPLAINT4001", "신고내역을 찾지 못했습니다."),
    COMMENTCOMPLAINT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT4001", "신고내역을 찾지 못했습니다."),
    PINCOMPLAINT_NOT_FOUND(HttpStatus.NOT_FOUND, "PIN4001", "신고내역을 찾지 못했습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }



    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
