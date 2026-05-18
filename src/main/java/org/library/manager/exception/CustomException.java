package org.library.manager.exception;

import lombok.Getter;
import lombok.Setter;
import org.library.manager.enums.ErrorCode;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
