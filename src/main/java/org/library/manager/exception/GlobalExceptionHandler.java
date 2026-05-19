package org.library.manager.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.manager.model.response.ApiResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(value = CustomException.class)
    ResponseEntity<ApiResponse<?>> handlingCustomException(CustomException exception){
        int code;
        String message;
        HttpStatusCode httpStatusCode;
        if (exception.getErrorCode() != null) {
            code = exception.getErrorCode().getCode();
            message = getMessage(exception.getErrorCode().getMessage());
            httpStatusCode = exception.getErrorCode().getHttpStatusCode();
        } else {
            String codeAndMessage = getMessage(exception.getMessage(), exception.getArgs());
            try {
                code = Integer.parseInt(codeAndMessage.split(":")[0]);
                message = codeAndMessage.split(":")[1];
            } catch (Exception e) {
                log.error("Error code is missing", e);
                code = -1;
                message = codeAndMessage;
            }
            httpStatusCode = exception.getHttpStatusCode();
        }

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(code)
                .result(message)
                .build();

        return ResponseEntity
                .status(httpStatusCode)
                .body(apiResponse);
    }

    private String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException ex) {
            log.error("Message code not found: {}", code, ex);
            return code;
        }
    }
}
