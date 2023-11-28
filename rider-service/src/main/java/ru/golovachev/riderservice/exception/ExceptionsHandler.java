package ru.golovachev.riderservice.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @RequiredArgsConstructor
    public static class ErrorInfo {
        public final String url;
        public final String message;
    }
}
