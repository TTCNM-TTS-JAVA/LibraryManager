package org.library.manager.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatusCode httpStatusCode;
    private Object[] args;
    private ErrorCode errorCode;

    public CustomException(
            String message,
            HttpStatusCode httpStatusCode
    ) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public CustomException(
            String message,
            int statusCode
    ) {
        super(message);
        this.message = message;
        this.httpStatusCode = HttpStatusCode.valueOf(statusCode);
    }

    public CustomException(String message, HttpStatusCode httpStatusCode, Object... args) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.args = args;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
        this.httpStatusCode = errorCode.getHttpStatusCode();
    }
}
