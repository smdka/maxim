package ru.golovachev.riderservice.exception;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(Long id) {
        super(String.format("Rider with id %d not found", id));
    }
}
