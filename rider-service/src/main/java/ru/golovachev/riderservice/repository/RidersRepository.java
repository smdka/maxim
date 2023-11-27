package ru.golovachev.riderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golovachev.riderservice.model.Rider;

import java.util.UUID;

@Repository
public interface RidersRepository extends JpaRepository<Rider, UUID> {
}
