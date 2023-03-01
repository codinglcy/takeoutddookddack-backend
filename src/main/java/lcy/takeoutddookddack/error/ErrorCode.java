package lcy.takeoutddookddack.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    INVALID_TOKEN(BAD_REQUEST, "유효하지 않은 토큰입니다."),

    SELLERID_NOT_FOUND(NOT_FOUND,"존재하지 않는 아이디입니다."),

    UNCORRECT_PASSWORD(UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다.");


    private final HttpStatus httpStatus;
    private final String detail;

}
