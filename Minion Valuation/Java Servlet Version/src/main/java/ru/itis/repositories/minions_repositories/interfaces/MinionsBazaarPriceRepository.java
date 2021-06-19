package ru.itis.repositories.minions_repositories.interfaces;

import javafx.util.Pair;
import ru.itis.models.Item;
import ru.itis.repositories.CrudRepository;

import java.util.Optional;

public interface MinionsBazaarPriceRepository extends CrudRepository<Item> {
    Optional<Item> getEntryByItemName(String itemName);
}
