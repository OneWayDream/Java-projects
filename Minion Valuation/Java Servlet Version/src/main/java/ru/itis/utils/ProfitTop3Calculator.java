package ru.itis.utils;

import ru.itis.models.Item;
import ru.itis.models.Minion;
import ru.itis.models.Production;

import java.util.List;

public class ProfitTop3Calculator {

    public static Double profitCount(Minion minion, int tier, List<Production> minionsProduction, List<Item> prices, Long time, boolean isBazaar){
        double result = 0.0;
        int numOfActions = (int) (time/(minion.getTimeBetweenActions().get(tier-1)*2));
        if (minion.getName().equals("FISHING")){
            numOfActions*=2;
        }
        for (Production production: minionsProduction) {
            if (minion.getName().trim().equals("IRON_MINION")){
                production.setItemName("IRON_INGOT");
            } else if (minion.getName().trim().equals("GOLD_MINION")){
                production.setItemName("GOLD_INGOT");
            }
            for (Item item: prices){
                if (item.getItemName().trim().equals(production.getItemName().trim())){
                    if (isBazaar){
                        result += numOfActions*production.getChance()*production.getAmount()*item.getBuyBazaarPrice()/100;
                    } else {
                        result += numOfActions*production.getChance()*production.getAmount()*item.getSellNPSPrice()/100;
                    }
                    break;
                }
            }
        }
        return result;
    }

}
