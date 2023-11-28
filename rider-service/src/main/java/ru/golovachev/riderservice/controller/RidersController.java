package ru.golovachev.riderservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.golovachev.riderservice.model.Rider;
import ru.golovachev.riderservice.service.RidersService;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/riders")
public class RidersController {
    private RidersService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createRider(@RequestBody Rider rider) {
        return service.save(rider).getId();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Rider updateRider(@RequestBody Rider rider) {
        return service.save(rider);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Rider> getRiders() {
        return service.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Rider> getRiders(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rider getRiderById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRider(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
