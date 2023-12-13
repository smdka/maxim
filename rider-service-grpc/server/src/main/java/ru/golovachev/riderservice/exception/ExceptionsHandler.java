package ru.golovachev.riderservice.exception;

import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@Slf4j
@GrpcAdvice
public class ExceptionsHandler {
    @GrpcExceptionHandler
    public Status handleNotFoundException(RiderNotFoundException e) {
        log.error(e.getMessage());
        return Status.NOT_FOUND
                .withDescription(e.getMessage())
                .withCause(e);
    }

    @GrpcExceptionHandler
    public Status handleNotFoundException(RiderAlreadyExistsException e) {
        log.error(e.getMessage());
        return Status.ALREADY_EXISTS
                .withDescription(e.getMessage())
                .withCause(e);
    }
}
