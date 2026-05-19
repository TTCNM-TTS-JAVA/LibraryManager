package org.library.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error", HttpStatus.BAD_REQUEST),
    AUTHOR_EXISTED(1001,"Author existed.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_EXISTED(1002,"Member existed.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1004,"Invalid message key", HttpStatus.BAD_REQUEST),
    AUTHOR_NOT_EXISTED(1005,"Author not existed.", HttpStatus.NOT_FOUND),
    MEMBER_NOT_EXISTED(1006,"Member not existed.", HttpStatus.NOT_FOUND),
    INVALID_JSON_FORMAT(1008, "Định dạng dữ liệu JSON không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_AUTHOR_STATUS(1009, "Trạng thái không hợp lệ. Chỉ chấp nhận: ACTIVE, INACTIVE, PENDING", HttpStatus.BAD_REQUEST);

    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
