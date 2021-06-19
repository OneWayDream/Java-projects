package ru.itis.repositories.minions_repositories.interfaces;

import ru.itis.models.Production;
import ru.itis.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MinionsProductionRepository extends CrudRepository<Production> {
    List<Production> getProductionsByMinionName(String minion_name);
}
