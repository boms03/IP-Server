package hackaton.ip_backend.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    // 200 : 요청 성공
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    // 400 : Request, Response 오류

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다."),

    EMPTY_JWT(false, HttpStatus.UNAUTHORIZED.value(), "JWT를 입력해주세요."),
    INVALID_JWT(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 JWT입니다."),
    IP_NOT_ENOUGH(false, HttpStatus.BAD_REQUEST.value(), "보유 ip가 부족합니다."),

    SURVEY_NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "Survey를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "Member를 찾을 수 없습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
