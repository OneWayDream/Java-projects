package ru.itis.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.SessionCookiesRepository;
import ru.itis.repositories.SessionCookiesRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJDBCImpl;
import ru.itis.repositories.minions_repositories.implementations.MinionsBazaarPriceRepositoryImpl;
import ru.itis.repositories.minions_repositories.implementations.MinionsNPSPriceRepositoryImpl;
import ru.itis.repositories.minions_repositories.implementations.MinionsProductionRepositoryImpl;
import ru.itis.repositories.minions_repositories.implementations.MinionsRepositoryImpl;
import ru.itis.repositories.minions_repositories.interfaces.MinionsBazaarPriceRepository;
import ru.itis.repositories.minions_repositories.interfaces.MinionsNPSPriceRepository;
import ru.itis.repositories.minions_repositories.interfaces.MinionsProductionRepository;
import ru.itis.repositories.minions_repositories.interfaces.MinionsRepository;
import ru.itis.services.SecurityService;
import ru.itis.services.SecurityServiceImpl;

import java.util.UUID;


public class Test {
    public static void main(String[] args) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/Minion_Valuation");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Ki27082001rill");
        hikariConfig.setMaximumPoolSize(10);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        MinionsRepository minionsRepository = new MinionsRepositoryImpl(dataSource);
        MinionsProductionRepository minionsProductionRepository = new MinionsProductionRepositoryImpl(dataSource);
        MinionsNPSPriceRepository minionsNPSPriceRepository = new MinionsNPSPriceRepositoryImpl(dataSource);
        MinionsBazaarPriceRepository minionsBazaarPriceRepository = new MinionsBazaarPriceRepositoryImpl(dataSource);
        SessionCookiesRepository sessionCookiesRepository = new SessionCookiesRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UsersRepositoryJDBCImpl(dataSource);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SecurityService securityService = new SecurityServiceImpl(sessionCookiesRepository, usersRepository, passwordEncoder);
        //        Top3Updater.updateData(minionsRepository.findAll(), minionsProductionRepository.findAll(),
//                minionsNPSPriceRepository.findAll(), false, null);
//        Top3Updater.updateData(minionsRepository.findAll(), minionsProductionRepository.findAll(),
//                minionsBazaarPriceRepository.findAll(), true, null);
        //System.out.println(minionsBazaarPriceRepository.getEntryByItemName(null));
        //System.out.println(UUID.randomUUID().toString());
        //securityService.signIn(6L);
        System.out.println(usersRepository.findAll());
    }
}
