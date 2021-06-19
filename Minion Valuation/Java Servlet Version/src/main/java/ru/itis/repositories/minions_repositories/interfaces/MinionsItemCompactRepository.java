package ru.itis.repositories.minions_repositories.interfaces;

import ru.itis.models.ItemAction;
import ru.itis.repositories.CrudRepository;

import java.util.Optional;

public interface MinionsItemCompactRepository extends CrudRepository<ItemAction> {
    Optional<ItemAction> getItemCompactByItemName(String itemName);
}
