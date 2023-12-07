package ru.golovachev.riderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(String id) {
        super(String.format("Rider with id %s not found", id));
    }
}
