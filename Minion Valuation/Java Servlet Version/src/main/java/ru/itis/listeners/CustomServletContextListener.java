package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.SessionCookiesRepository;
import ru.itis.repositories.SessionCookiesRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJDBCImpl;
import ru.itis.repositories.minions_repositories.implementations.*;
import ru.itis.services.*;
import ru.itis.utils.MinionsBazaarPricesUpdater;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


@WebListener
public class CustomServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();


        //Database properties
        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/classes/db.properties"));
        } catch (IOException|NullPointerException e) {
            throw new IllegalArgumentException("Exception while loading database properties", e);
        }

        //Top 3 properties
        Properties top3Properties = new Properties();
        try {
            top3Properties.load(servletContext.getResourceAsStream("/WEB-INF/classes/top3.properties"));
        } catch (IOException|NullPointerException e) {
            throw new IllegalArgumentException("Exception while loading top 3 properties", e);
        }

        servletContext.setAttribute("top3Properties", top3Properties);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryJDBCImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        servletContext.setAttribute("usersService", usersService);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        servletContext.setAttribute("passwordEncoder", passwordEncoder);

        SessionCookiesRepository sessionCookiesRepository = new SessionCookiesRepositoryImpl(dataSource);
        SecurityService securityService = new SecurityServiceImpl(sessionCookiesRepository, usersRepository, passwordEncoder);
        servletContext.setAttribute("securityService", securityService);

        MinionsDataService minionsDataService = new MinionsDataServiceImpl(new MinionsBazaarPriceRepositoryImpl(dataSource
        ), new MinionsFuelsRepositoryImpl(dataSource), new MinionsItemCompactRepositoryImpl(dataSource),
                new MinionsItemSmeltRepositoryImpl(dataSource), new MinionsItemSuperCompactRepositoryImpl(dataSource),
                new MinionsNPSPriceRepositoryImpl(dataSource), new MinionsProductionRepositoryImpl(dataSource),
                new MinionsRepositoryImpl(dataSource), new MinionsUpgradeGroupsRepositoryImpl(dataSource),
                new MinionsUpgradesImpl(dataSource));
        servletContext.setAttribute("minionsDataService", minionsDataService);

        Thread thread = new Thread(new MinionsBazaarPricesUpdater(minionsDataService,
                new HypixelBazaarPriceService(properties.getProperty("hypixel.connection.key")),
                true,
                1200000L,
                servletContext));
        thread.start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
