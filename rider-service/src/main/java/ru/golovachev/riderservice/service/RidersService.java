package ru.golovachev.riderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.golovachev.riderservice.model.Rider;

import java.util.Collection;
import java.util.UUID;

public interface RidersService {
    Rider save(Rider rider);

    Collection<Rider> findAll();

    Page<Rider> findAll(Pageable pageable);

    Rider findById(UUID id);

    void deleteById(UUID id);

}
