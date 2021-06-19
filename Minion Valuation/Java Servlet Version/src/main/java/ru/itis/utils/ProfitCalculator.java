package ru.itis.utils;

import javafx.util.Pair;
import ru.itis.exceptions.NoSuchEntityException;
import ru.itis.models.*;
import ru.itis.services.MinionsDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfitCalculator {
    public static boolean isCommonUpgrade(String upgradeName, List<Upgrade> upgrades) {
        boolean result = false;
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getName().equals(upgradeName)) {
                if (upgrade.getGroupName().equals("ALL")) {
                    result = true;
                }
                break;
            }
        }
        return result;
    }

    public static int checkFormData(MinionsDataService minionsDataService, String minionName, String upgrade1Name, String upgrade2Name) {
        int result = 5;
        if (minionName.equals("")) {
            result = 1;
        } else {
            List<String> minionUpgrades = minionsDataService.getUpgradesForMinion(minionName);
            List<Upgrade> upgrades = minionsDataService.getAllUpgrades();
            if ((upgrade1Name.equals(""))||(minionUpgrades.contains(upgrade1Name)) || (ProfitCalculator.isCommonUpgrade(upgrade1Name, upgrades))) {
                if ((upgrade2Name.equals(""))||(minionUpgrades.contains(upgrade2Name)) || (ProfitCalculator.isCommonUpgrade(upgrade2Name, upgrades))) {
                    if ((upgrade1Name.equals(upgrade2Name))&&(!upgrade1Name.equals("")) && ((!upgrade1Name.equals("FLYCATCHER")) && (!upgrade1Name.equals("MINION_EXPANDER")))) {
                        result = 4;
                    }
                } else {
                    result = 3;
                }
            } else {
                result = 2;
            }
        }
        return result;
    }

    public static double calculateProfit(MinionsDataService minionsDataService, String minionName, int tier, String fuelName,
                                         String upgrade1Name, String upgrade2Name) {
        if ((fuelName!=null)&&(fuelName.equals(""))){
            fuelName = null;
        }
        if ((upgrade1Name!=null)&&(upgrade1Name.equals(""))){
            upgrade1Name = null;
        }
        if ((upgrade2Name!=null)&&(upgrade2Name.equals(""))){
            upgrade2Name = null;
        }
        boolean isUsed = fuelName == null;
        boolean isUsed1 = upgrade1Name == null;
        boolean isUsed2 = upgrade2Name == null;
        Minion minion = minionsDataService.getMinionByName(minionName).orElseThrow(()-> new NoSuchEntityException("Unexpected minion name"));
        double timeOfAction = minion.getTimeBetweenActions().get(tier - 1);
        int k = 0;
        if (!isUsed1) {
            if (upgrade1Name.equals("FLYCATCHER")) {
                k += 20;
                isUsed1 = true;
            } else if (upgrade1Name.equals("MINION_EXPANDER")) {
                k += 5;
                isUsed1 = true;
            }
        } //using flycatchers and expanders
        if (!isUsed2) {
            if (upgrade2Name.equals("FLYCATCHER")) {
                k += 20;
                isUsed2 = true;
            } else if (upgrade2Name.equals("MINION_EXPANDER")) {
                k += 5;
                isUsed2 = true;
            }
        } //using flycatchers and expanders
        if (!isUsed1) {
            if (upgrade1Name.equals(upgrade2Name)) {
                isUsed1 = true;
            }
        } //delete duplicates
        if (!isUsed) {
            if (!(fuelName.equals("CATALYST")) && !(fuelName.equals("HYPER_CATALYST"))) {
                Fuel fuel = minionsDataService.getFuelByName(fuelName).orElseThrow(()-> new NoSuchEntityException("Unexpected fuel name"));
                k += fuel.getSpeedBoost();
                isUsed = true;
            }
        } //using common fuel
        timeOfAction = (timeOfAction) / (1 + (double) k / 100);
        if (!minionName.equals("FISHING_MINION")) {
            timeOfAction *= 2;
        }
        double productAmount = (60 * 60 * 24) / timeOfAction;
        if (!isUsed) {
            Fuel fuel = minionsDataService.getFuelByName(fuelName).orElseThrow(()-> new NoSuchEntityException("Unexpected fuel name"));
            productAmount *= (double) fuel.getSpeedBoost() / 100;
        } //using catalyst fuel
        List<Production> productions = minionsDataService.getProductionsForMinion(minionName);
        List<Pair<String, Double>> minionProduction = new ArrayList<>();
        for (Production production : productions) {
            minionProduction.add(new Pair<>(production.getItemName().trim(), productAmount * production.getChance() * production.getAmount() / 100));
        }
        if (!isUsed1) {
            isUsed1 = useEGFSDS(minionsDataService, isUsed1, upgrade1Name, minionProduction, productAmount);
        } //using enchanted egg, flint shovel and diamond spreading.
        if (!isUsed2) {
            isUsed2 = useEGFSDS(minionsDataService, isUsed2, upgrade2Name, minionProduction, productAmount);
        } //using enchanted egg, flint shovel and diamond spreading.
        if (!isUsed1) {
            isUsed1 = useSmelter(minionsDataService, isUsed1, upgrade1Name, minionProduction);
        } //using smelter
        if (!isUsed2) {
            isUsed2 = useSmelter(minionsDataService, isUsed2, upgrade2Name, minionProduction);
        } //using smelter
        if (!isUsed1) {
            isUsed1 = useCompactor(minionsDataService, isUsed1, upgrade1Name, minionProduction);
        } //using compactor
        if (!isUsed2) {
            isUsed2 = useCompactor(minionsDataService, isUsed2, upgrade2Name, minionProduction);
        } //using compactor
        if (!isUsed1) {
            isUsed1 = useSuperCompactor(minionsDataService, isUsed1, upgrade1Name, minionProduction);
        } //using super compactor
        if (!isUsed2){
            isUsed2 = useSuperCompactor(minionsDataService, isUsed2, upgrade2Name, minionProduction);
        } //using super compactor
        double profit = 0;
        for (Pair<String, Double> pair: minionProduction) {
            Item item = minionsDataService.getItemBazaarPrice(pair.getKey()).orElse(Item.builder().build());
            if (item.getSellBazaarPrice()!=null){
                profit+=pair.getValue()*item.getSellBazaarPrice();
            } else {
                item = minionsDataService.getItemNPSPrice(pair.getKey()).orElse(Item.builder().build());
                if (item.getSellNPSPrice()!=null){
                    profit+=pair.getValue()*item.getSellNPSPrice();
                }
            }
        }
        return profit;
    }

    public static boolean useEGFSDS(MinionsDataService minionsDataService, boolean isUsed, String upgradeName, List<Pair<String, Double>> minionProduction, Double productAmount) {
        if (upgradeName.equals("ENCHANTED_EGG")) {
            minionProduction.add(new Pair<>("EGG", productAmount));
            isUsed = true;
        }
        if (upgradeName.equals("FLINT_SHOVEL")) {
            for (int i = 0; i < minionProduction.size(); i++) {
                if (minionProduction.get(i).getKey().equals("GRAVEL")) {
                    minionProduction.add(new Pair<>("FLINT", minionProduction.get(i).getValue()));
                    minionProduction.remove(i);
                    i--;
                }
            }
            isUsed = true;
        }
        if (upgradeName.equals("DIAMOND_SPREADING")) {
            minionProduction.add(new Pair<>("DIAMOND", productAmount * 0.1 * 1));
            isUsed = true;
        }
        return isUsed;
    }

    public static boolean useSmelter(MinionsDataService minionsDataService, boolean isUsed, String upgradeName, List<Pair<String, Double>> minionProduction) {
        if (upgradeName.equals("AUTO_SMELTER")) {
            for (int i = 0; i < minionProduction.size(); i++) {
                ItemAction itemAction = minionsDataService.getItemSmeltByItemName(minionProduction.get(i).getKey()).orElse(ItemAction.builder().build());
                if (itemAction.getResultName() != null) {
                    minionProduction.add(new Pair<>(itemAction.getResultName().trim(), minionProduction.get(i).getValue() * itemAction.getOutAmount() / itemAction.getInAmount()));
                    minionProduction.remove(i);
                    i--;
                }
            }
            isUsed = true;
        }
        return isUsed;
    }

    public static boolean useCompactor(MinionsDataService minionsDataService, boolean isUsed, String upgradeName, List<Pair<String, Double>> minionProduction) {
        if (upgradeName.equals("COMPACTOR")) {
            for (int i = 0; i < minionProduction.size(); i++) {
                ItemAction itemAction = minionsDataService.getItemCompactByItemName(minionProduction.get(i).getKey()).orElse(ItemAction.builder().build());
                if (itemAction.getResultName() != null) {
                    minionProduction.add(new Pair<>(itemAction.getResultName().trim(), minionProduction.get(i).getValue() * itemAction.getOutAmount() / itemAction.getInAmount()));
                    minionProduction.remove(i);
                    i--;
                }
            }
            isUsed = true;
        }
        return isUsed;
    }

    public static boolean useSuperCompactor(MinionsDataService minionsDataService, boolean isUsed, String upgradeName, List<Pair<String, Double>> minionProduction) {
        if (upgradeName.equals("SUPER_COMPACTOR_3000")) {
            for (int i = 0; i < minionProduction.size(); i++) {
                ItemAction itemAction = minionsDataService.getItemSuperCompactByItemName(minionProduction.get(i).getKey()).orElse(ItemAction.builder().build());
                if (itemAction.getResultName() != null) {
                    minionProduction.add(new Pair<>(itemAction.getResultName().trim(), minionProduction.get(i).getValue() * itemAction.getOutAmount() / itemAction.getInAmount()));
                    minionProduction.remove(i);
                    i--;
                }
            }
            isUsed = true;
        }
        return isUsed;
    }
}
