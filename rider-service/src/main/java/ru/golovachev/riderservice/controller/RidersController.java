package ru.golovachev.riderservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.golovachev.riderservice.model.Rider;
import ru.golovachev.riderservice.repository.RidersRepository;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/riders")
public class RidersController {
    private RidersRepository repository;

    @PostMapping
    public UUID createRider(@RequestBody Rider rider) {
        return repository.save(rider).getId();
    }

    @PatchMapping
    public Rider updateRider(@RequestBody Rider rider) {
        return repository.save(rider);
    }

    @GetMapping
    public Collection<Rider> getRiders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Rider getRiderById(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteRider(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
