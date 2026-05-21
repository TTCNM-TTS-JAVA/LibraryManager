package org.library.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5000, "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),
    BOOK_CATEGORY_NOT_FOUND(4041, "book-category.not.found", HttpStatus.NOT_FOUND),
    BOOK_CATEGORY_NAME_DUPLICATED(4091, "book-category.name.duplicated", HttpStatus.CONFLICT),
    BOOK_CATEGORY_ALREADY_INACTIVE(4092, "book-category.already.inactive", HttpStatus.CONFLICT);


    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
