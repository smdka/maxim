package ru.golovachev.riderservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({Exception.class})
    ErrorInfo handleException(Exception e, HttpServletRequest request) {
        log.error("Something went wrong: {}", e.getMessage());
        return ErrorInfo.builder()
                .url(request.getRequestURI())
                .message(e.getMessage())
                .build();
    }

    @Builder
    public static class ErrorInfo {
        private String url;
        private String message;
    }
}
