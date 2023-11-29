package ru.golovachev.riderservice.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.golovachev.riderservice.dto.RiderDto;
import ru.golovachev.riderservice.exception.RiderNotFoundException;
import ru.golovachev.riderservice.model.Rider;
import ru.golovachev.riderservice.repository.RidersRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RidersServiceImpl implements RidersService {
    private RidersRepository repository;

    @Override
    @Transactional
    public RiderDto save(RiderDto riderDto) {
        Rider rider = RiderMapper.INSTANCE.toModel(riderDto);
        return RiderMapper.INSTANCE.toDto(repository.save(rider));
    }

    @Override
    @Transactional
    public RiderDto update(RiderDto riderDto, UUID id) {
        return repository.findById(id)
                .map(rider -> {
                    RiderMapper.INSTANCE.updateFromDto(riderDto, rider);
                    return repository.save(rider);
                })
                .map(RiderMapper.INSTANCE::toDto)
                .orElseThrow(() -> new RiderNotFoundException(id.toString()));
    }


    @Override
    public Collection<RiderDto> findAll() {
        return repository.findAll().stream()
                .map(RiderMapper.INSTANCE::toDto)
                .collect(toList());
    }

    @Override
    public Page<RiderDto> findAll(Pageable pageable) {
        List<RiderDto> riderDtos = repository.findAll(pageable).stream()
                .map(RiderMapper.INSTANCE::toDto)
                .collect(toList());
        return new PageImpl<>(riderDtos);
    }

    @Override
    public RiderDto findById(UUID id) {
        return repository.findById(id)
                .map(RiderMapper.INSTANCE::toDto)
                .orElseThrow(() -> new RiderNotFoundException(id.toString()));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
