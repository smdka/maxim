package ru.golovachev.riderservice.exception;

import lombok.Builder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @Builder
    public static class ErrorInfo {
        private String url;
        private String message;
    }
}
