package ru.golovachev.riderservice.validation;

import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import ru.golovachev.riderservice.dto.RiderDto;
import ru.golovachev.riderservice.service.Riders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
@GrpcGlobalServerInterceptor
public class ValidationInterceptor implements ServerInterceptor {


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {

        log.info(call.getMethodDescriptor().getFullMethodName());
        log.info(call.getAttributes().toString());
        log.info(headers.toString());

        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);

        if ("riderservice.service.RidersService/createRider".equals(call.getMethodDescriptor().getFullMethodName())) {
            log.info("Validating request");
            listener = new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(listener) {
                @Override
                public void onMessage(ReqT message) {
                    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                    ExecutableValidator validator = validatorFactory.getValidator().forExecutables();

                    RiderDto riderDto = ((Riders.CreateRiderRequest) message).getRiderDto();

                    try {
                        Method method = RiderDto.class.getMethod("getEmail");
                        Set<ConstraintViolation<RiderDto>> violations = validator.validateReturnValue(riderDto, method, riderDto);
                        if (!violations.isEmpty()) {
                            violations.forEach(v -> log.error("Validation failed for {}: {}", v.getPropertyPath(), v.getMessage()));
                            call.close(Status.INVALID_ARGUMENT, headers);
                        } else {
                            super.onMessage(message);
                        }
                    } catch (NoSuchMethodException | ValidationException e) {
                        call.close(Status.FAILED_PRECONDITION, headers);
                    }
                }
            };
        }

        return listener;
    }
}
