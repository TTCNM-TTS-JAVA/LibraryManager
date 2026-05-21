package org.library.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5000, "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),
    AUTHOR_NOT_FOUND(5001, "author.not.found", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_NOT_FOUND(5002, "member.not.found", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_EXISTED(5003,"member.existed",HttpStatus.FOUND),
    MEMBER_CODE_EXISTED(5005,"member.code.existed",HttpStatus.FOUND),
    MEMBER_NOT_EXISTED(5004, "member.not.existed", HttpStatus.NOT_FOUND)
    ;



    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
