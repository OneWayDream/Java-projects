package ru.itis.repositories.minions_repositories.interfaces;

import ru.itis.models.Minion;
import ru.itis.repositories.CrudRepository;

import java.util.Optional;

public interface MinionsRepository extends CrudRepository<Minion> {
    Optional<Minion> getMinionByName(String minion_name);
}
