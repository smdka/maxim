package ru.golovachev.riderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.golovachev.riderservice.model.Rider;

@Repository
public interface RidersRepository extends JpaRepository<Rider, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
