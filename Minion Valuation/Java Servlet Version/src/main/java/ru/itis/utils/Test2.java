package ru.itis.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.repositories.minions_repositories.implementations.*;
import ru.itis.services.MinionsDataService;
import ru.itis.services.MinionsDataServiceImpl;

public class Test2 {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/Minion_Valuation");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Ki27082001rill");
        hikariConfig.setMaximumPoolSize(10);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        MinionsDataService minionsDataService = new MinionsDataServiceImpl(new MinionsBazaarPriceRepositoryImpl(dataSource
        ), new MinionsFuelsRepositoryImpl(dataSource), new MinionsItemCompactRepositoryImpl(dataSource),
                new MinionsItemSmeltRepositoryImpl(dataSource), new MinionsItemSuperCompactRepositoryImpl(dataSource),
                new MinionsNPSPriceRepositoryImpl(dataSource), new MinionsProductionRepositoryImpl(dataSource),
                new MinionsRepositoryImpl(dataSource), new MinionsUpgradeGroupsRepositoryImpl(dataSource),
                new MinionsUpgradesImpl(dataSource));

        System.out.println(ProfitCalculator.calculateProfit(minionsDataService, "IRON_MINION", 11, null, "AUTO_SMELTER", "SUPER_COMPACTOR_3000"));
    }
}
