package ru.itis.persistence.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itis.persistence.criteria.CriteriaBuilder;
import ru.itis.persistence.criteria.SignsResolver;
import ru.itis.persistence.criteria.postgres.PostgresCriteriaBuilder;
import ru.itis.persistence.criteria.postgres.PostgresSignsResolver;

@Data
@AllArgsConstructor
public class EntityManagerFactory {

    protected Configuration configuration;

    public EntityManager createEntityManager(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(configuration.getUrl());
        hikariConfig.setDriverClassName(configuration.getDriverClass().getName());
        hikariConfig.setUsername(configuration.getUsername());
        hikariConfig.setPassword(configuration.getPassword());
        hikariConfig.setMaximumPoolSize(configuration.getMaximumPoolSize());
        return new EntityManager(new HikariDataSource(hikariConfig), getCriteriaBuilder(), configuration.getShowSql(), getSignsResolver());
    }

    public CriteriaBuilder getCriteriaBuilder(){
        switch (configuration.getDialect()){
            case PostgreSQL9Dialect:
            default:
                return new PostgresCriteriaBuilder();
        }
    }

    public SignsResolver getSignsResolver(){
        switch (configuration.getDialect()){
            case PostgreSQL9Dialect:
            default:
                return new PostgresSignsResolver();
        }
    }
}
