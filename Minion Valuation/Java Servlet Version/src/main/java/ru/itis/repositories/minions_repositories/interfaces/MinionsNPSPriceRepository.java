package ru.itis.repositories.minions_repositories.interfaces;

import ru.itis.models.Item;
import ru.itis.repositories.CrudRepository;

import java.util.Optional;

public interface MinionsNPSPriceRepository extends CrudRepository<Item> {
    Optional<Item> getEntryByItemName(String itemName);
}
