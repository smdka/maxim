package ru.golovachev.riderservice.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;
import ru.golovachev.riderservice.dto.RiderDto;
import ru.golovachev.riderservice.exception.RiderAlreadyExistsException;
import ru.golovachev.riderservice.exception.RiderNotFoundException;
import ru.golovachev.riderservice.model.Rider;
import ru.golovachev.riderservice.repository.RidersRepository;

import static ru.golovachev.riderservice.service.Riders.*;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class GrpcRidersServiceImpl extends RidersServiceGrpc.RidersServiceImplBase {
    private final RidersRepository repository;

    @Override
    @Transactional
    public void createRider(CreateRiderRequest request,
                            StreamObserver<CreateRiderResponse> responseObserver) {

        Rider rider = ProtoRiderMapper.INSTANCE.toModel(request.getRiderDto());

        String phoneNumber = rider.getPhoneNumber();
        String email = rider.getEmail();

        if (repository.existsByEmailOrPhoneNumber(email, phoneNumber)) {
            throw new RiderAlreadyExistsException("Phone number or email is invalid");
        }

        Long id = repository.save(rider).getId();

        log.info("Rider with id {} created, sending response", id);

        CreateRiderResponse response = CreateRiderResponse.newBuilder()
                .setId(id)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @Transactional(readOnly = true)
    public void findRiderById(FindRiderByIdRequest request,
                              StreamObserver<FindRiderByIdResponse> responseObserver) {

        long id = request.getId();

        RiderDto foundRiderDto = repository.findById(id)
                .map(ProtoRiderMapper.INSTANCE::toDto)
                .orElseThrow(() -> new RiderNotFoundException(id));

        log.info("Rider with id {} found, sending response", id);

        FindRiderByIdResponse response = FindRiderByIdResponse.newBuilder()
                .setRiderDto(foundRiderDto)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateRiderById(UpdateRiderByIdRequest request,
                                StreamObserver<UpdateRiderByIdResponse> responseObserver) {

        long id = request.getId();

        RiderDto updatedRiderDto = repository.findById(id)
                .map(rider -> {
                    ProtoRiderMapper.INSTANCE.updateFromDto(request.getRiderDto(), rider);
                    return repository.save(rider);
                })
                .map(ProtoRiderMapper.INSTANCE::toDto)
                .orElseThrow(() -> new RiderNotFoundException(id));

        log.info("Rider with id {} updated, sending response", id);

        UpdateRiderByIdResponse response = UpdateRiderByIdResponse.newBuilder()
                .setRiderDto(updatedRiderDto)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void deleteRiderById(DeleteRiderByIdRequest request,
                                StreamObserver<Empty> responseObserver) {

        long id = request.getId();

        if (!repository.existsById(id)) {
            throw new RiderNotFoundException(id);
        }

        repository.deleteById(id);

        log.info("Rider with id {} deleted, sending response", id);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
