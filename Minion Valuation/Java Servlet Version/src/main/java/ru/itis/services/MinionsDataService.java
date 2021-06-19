package ru.itis.services;

import javafx.util.Pair;
import ru.itis.exceptions.MinionsDataRepositoryException;
import ru.itis.models.*;

import java.util.List;
import java.util.Optional;

public interface MinionsDataService {
    List<Item> getBazaarPricesList();
    void updateBazaarPricesEntry(Item item);
    List<Minion> getAllMinions();
    List<Production> getAllProductions();
    List<Item> getNPSPricesList();
    List<Fuel> getAllFuels();
    List<Upgrade> getAllUpgrades();
    List<String> getUpgradesForMinion(String minionName);
    List<Production> getProductionsForMinion(String minionName);
    Optional<Minion> getMinionByName(String minionName);
    Optional<Fuel> getFuelByName(String fuelName);
    Optional<ItemAction> getItemSmeltByItemName(String itemName);
    Optional<ItemAction> getItemCompactByItemName(String itemName);
    Optional<ItemAction> getItemSuperCompactByItemName(String itemName);
    Optional<Item> getItemBazaarPrice(String itemName);
    Optional<Item> getItemNPSPrice(String itemName);
}
