package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.Upgrade;

import java.util.List;
import java.util.Optional;

public interface MinionUpgradesRepository extends JpaRepository<Upgrade, Long> {

    List<Upgrade> findAllByIsDeletedIsNull();

    Optional<Upgrade> findByName(String name);

    List<Upgrade> findAllByIsUniversalIsTrue();

}
