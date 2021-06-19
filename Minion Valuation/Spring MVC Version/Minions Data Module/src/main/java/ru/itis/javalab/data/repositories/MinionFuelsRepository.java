package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.Fuel;

import java.util.List;
import java.util.Optional;

public interface MinionFuelsRepository extends JpaRepository<Fuel, Long> {

    List<Fuel> findAllByIsDeletedIsNull();

    Optional<Fuel> findByName(String name);

}
