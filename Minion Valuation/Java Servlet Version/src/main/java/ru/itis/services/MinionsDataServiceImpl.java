package ru.itis.services;

import ru.itis.exceptions.MinionsDataRepositoryException;
import ru.itis.models.*;
import ru.itis.repositories.minions_repositories.interfaces.*;

import java.util.List;
import java.util.Optional;

public class MinionsDataServiceImpl implements MinionsDataService {

    protected MinionsBazaarPriceRepository minionsBazaarPriceRepository;
    protected MinionsFuelsRepository minionsFuelsRepository;
    protected MinionsItemCompactRepository minionsItemCompactRepository;
    protected MinionsItemSmeltRepository minionsItemSmeltRepository;
    protected MinionsItemSuperCompactRepository minionsItemSuperCompactRepository;
    protected MinionsNPSPriceRepository minionsNPSPriceRepository;
    protected MinionsProductionRepository minionsProductionRepository;
    protected MinionsRepository minionsRepository;
    protected MinionsUpgradeGroupsRepository minionsUpgradeGroupsRepository;
    protected MinionsUpgrades minionsUpgrades;

    public MinionsDataServiceImpl(MinionsBazaarPriceRepository minionsBazaarPriceRepository, MinionsFuelsRepository minionsFuelsRepository, MinionsItemCompactRepository minionsItemCompactRepository, MinionsItemSmeltRepository minionsItemSmeltRepository, MinionsItemSuperCompactRepository minionsItemSuperCompactRepository, MinionsNPSPriceRepository minionsNPSPriceRepository, MinionsProductionRepository minionsProductionRepository, MinionsRepository minionsRepository, MinionsUpgradeGroupsRepository minionsUpgradeGroupsRepository, MinionsUpgrades minionsUpgrades) {
        this.minionsBazaarPriceRepository = minionsBazaarPriceRepository;
        this.minionsFuelsRepository = minionsFuelsRepository;
        this.minionsItemCompactRepository = minionsItemCompactRepository;
        this.minionsItemSmeltRepository = minionsItemSmeltRepository;
        this.minionsItemSuperCompactRepository = minionsItemSuperCompactRepository;
        this.minionsNPSPriceRepository = minionsNPSPriceRepository;
        this.minionsProductionRepository = minionsProductionRepository;
        this.minionsRepository = minionsRepository;
        this.minionsUpgradeGroupsRepository = minionsUpgradeGroupsRepository;
        this.minionsUpgrades = minionsUpgrades;
    }

    @Override
    public List<Item> getBazaarPricesList() throws MinionsDataRepositoryException {
        try {
            return minionsBazaarPriceRepository.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public void updateBazaarPricesEntry(Item item) {
        try {
            minionsBazaarPriceRepository.update(item);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Minion> getAllMinions() {
        try {
            return minionsRepository.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Production> getAllProductions() {
        try {
            return minionsProductionRepository.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Item> getNPSPricesList() {
        try {
            return minionsNPSPriceRepository.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Fuel> getAllFuels() {
        try {
            return minionsFuelsRepository.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Upgrade> getAllUpgrades() {
        try {
            return minionsUpgrades.findAll();
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<String> getUpgradesForMinion(String minionName) {
        try {
            return minionsUpgradeGroupsRepository.getUpgradesMyMinionName(minionName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public List<Production> getProductionsForMinion(String minionName) {
        try {
            return minionsProductionRepository.getProductionsByMinionName(minionName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<Minion> getMinionByName(String minionName) {
        try {
            return minionsRepository.getMinionByName(minionName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<Fuel> getFuelByName(String fuelName) {
        try {
            return minionsFuelsRepository.findByFuelName(fuelName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<ItemAction> getItemSmeltByItemName(String itemName) {
        try {
            return minionsItemSmeltRepository.getItemSmeltByItemName(itemName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<ItemAction> getItemCompactByItemName(String itemName) {
        try {
            return minionsItemCompactRepository.getItemCompactByItemName(itemName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<ItemAction> getItemSuperCompactByItemName(String itemName) {
        try {
            return minionsItemSuperCompactRepository.findItemSuperCompactByItemName(itemName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<Item> getItemBazaarPrice(String itemName) {
        try {
            return minionsBazaarPriceRepository.getEntryByItemName(itemName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }

    @Override
    public Optional<Item> getItemNPSPrice(String itemName) {
        try {
            return minionsNPSPriceRepository.getEntryByItemName(itemName);
        } catch (IllegalStateException ex){
            throw new MinionsDataRepositoryException(ex);
        }
    }
}
