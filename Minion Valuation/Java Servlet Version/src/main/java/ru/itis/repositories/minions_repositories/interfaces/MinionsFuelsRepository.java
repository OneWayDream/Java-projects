package ru.itis.repositories.minions_repositories.interfaces;

import ru.itis.models.Fuel;
import ru.itis.repositories.CrudRepository;


import java.util.Optional;

public interface MinionsFuelsRepository extends CrudRepository<Fuel> {
    Optional<Fuel> findByFuelName(String fuel_name);
}
