package ru.itis.utils;

import ru.itis.models.Item;
import ru.itis.models.Minion;
import ru.itis.models.Production;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Top3Updater {

    public static void updateData(List<Minion> minions, List<Production> productions, List<Item> prices,
                                  boolean isBazaar, ServletContext servletContext){
        Properties properties = (Properties) servletContext.getAttribute("top3Properties");
        String[] topMinions = new String[3];
        Integer[] topProfit = new Integer[3];
        topProfit[0] = 0;
        topProfit[1] = 0;
        topProfit[2] = 0;

        List<ArrayList<Production>> minionsProduction = new ArrayList<>();
        for (int i = 0; i < minions.size(); i++){
            minionsProduction.add(new ArrayList<>());
        }
        List<String> minionNames = minions.stream().map(Minion::getName).collect(Collectors.toList());
        for (Production production: productions) {
            minionsProduction.get(minionNames.indexOf(production.getMinionName())).add(production);
        }
        for (int i = 0; i < minions.size(); i++){
            Minion minion = minions.get(i);
            addData(topMinions, topProfit, minion.getName(),
                     ProfitTop3Calculator.profitCount(minion, 11, minionsProduction.get(i), prices, 60*60*24L, isBazaar).intValue());
        }
        for (int i = 0; i < 3; i++){
            System.out.println(topMinions[i] + " for " + topProfit[i]);
        }
        if (isBazaar){
            properties.setProperty("top1bazaar", topMinions[0].trim());
            properties.setProperty("top2bazaar", topMinions[1].trim());
            properties.setProperty("top3bazaar", topMinions[2].trim());
            properties.setProperty("top1bazaarProfit", String.valueOf(topProfit[0]));
            properties.setProperty("top2bazaarProfit", String.valueOf(topProfit[1]));
            properties.setProperty("top3bazaarProfit", String.valueOf(topProfit[2]));
        } else {
            properties.setProperty("top1nps", topMinions[0].trim());
            properties.setProperty("top2nps", topMinions[1].trim());
            properties.setProperty("top3nps", topMinions[2].trim());
            properties.setProperty("top1npsProfit", String.valueOf(topProfit[0]));
            properties.setProperty("top2npsProfit", String.valueOf(topProfit[1]));
            properties.setProperty("top3npsProfit", String.valueOf(topProfit[2]));
        }
        try{
            properties.store(new FileOutputStream(properties.getProperty("path")), null);
        } catch (IOException ex){
            servletContext.setAttribute("dataDownloadError", true);
        }
    }

    private static void addData(String[] topMinions, Integer[] topProfit, String minion, Integer profit){
        if (profit>topProfit[2]){
            if (profit>topProfit[1]){
                if (profit>topProfit[0]){
                    topProfit[2] = topProfit[1];
                    topMinions[2] = topMinions[1];
                    topMinions[1] = topMinions[0];
                    topProfit[1] = topProfit[0];
                    topProfit[0] = profit;
                    topMinions[0] = minion;
                } else {
                    topProfit[2] = topProfit[1];
                    topMinions[2] = topMinions[1];
                    topProfit[1] = profit;
                    topMinions[1] = minion;
                }
            } else {
                topProfit[2] = profit;
                topMinions[2] = minion;
            }
        }
    }

}
