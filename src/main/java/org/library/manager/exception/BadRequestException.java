package org.library.manager.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, Object... args) {
        super(message, HttpStatus.BAD_REQUEST, args);
    }
}
