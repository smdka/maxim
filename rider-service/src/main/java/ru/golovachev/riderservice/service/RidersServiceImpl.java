package ru.golovachev.riderservice.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.golovachev.riderservice.exception.RiderNotFoundException;
import ru.golovachev.riderservice.model.Rider;
import ru.golovachev.riderservice.repository.RidersRepository;

import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RidersServiceImpl implements RidersService {
    private RidersRepository repository;

    @Override
    public Rider save(Rider rider) {
        return repository.save(rider);
    }

    @Override
    public Collection<Rider> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Rider> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Rider findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException(id.toString()));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
