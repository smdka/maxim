package ru.golovachev.riderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.golovachev.riderservice.dto.RiderDto;

import java.util.UUID;

public interface RidersService {
    RiderDto save(RiderDto riderDto);

    RiderDto update(RiderDto riderDto, UUID id);

    Page<RiderDto> findAll(Pageable pageable);

    RiderDto findById(UUID id);

    void deleteById(UUID id);

}
