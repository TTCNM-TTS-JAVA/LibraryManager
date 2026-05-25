package org.library.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5000, "internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),
    PUBLISHER_NOT_FOUND(4041, "book-category.not.found", HttpStatus.NOT_FOUND),
    PUBLISHER_NAME_DUPLICATED(4091, "book-category.name.duplicated", HttpStatus.CONFLICT),
    PUBLISHER_ALREADY_INACTIVE(4092, "book-category.already.inactive", HttpStatus.CONFLICT),

    TOTAL_QUANTITY_LESS_THAN_BORROWED(4002, "total.quantity.less.than.borrowed", HttpStatus.BAD_REQUEST),

    AUTHOR_NOT_FOUND(5001, "author.not.found", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_RETURN_QUANTITY(40010, "book.return.exceeds.borrowed", HttpStatus.BAD_REQUEST),
    AUTHOR_NOT_ALREADY_EXIST(4001, "author.not.already.exists", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_ALREADY_EXIST(4002, "category.not.already.exists", HttpStatus.NOT_FOUND),
    PUBLISHER_NOT_ALREADY_EXIST(4003, "publisher.not.already.exists", HttpStatus.NOT_FOUND),
    MEMBER_NOT_FOUND(5002, "member.not.found", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_EXISTED(5003,"member.existed",HttpStatus.FOUND),
    BOOK_CODE_EXISTED(5006,"member.existed",HttpStatus.FOUND),
    MEMBER_CODE_EXISTED(5005,"member.code.existed",HttpStatus.FOUND),
    MEMBER_NOT_EXISTED(4004, "member.not.existed", HttpStatus.NOT_FOUND),
    BOOK_NOT_EXISTED(4005, "book.not.existed", HttpStatus.NOT_FOUND),
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
