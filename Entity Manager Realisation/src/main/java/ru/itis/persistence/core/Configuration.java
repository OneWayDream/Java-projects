package ru.itis.persistence.core;

import lombok.Builder;
import lombok.Data;
import ru.itis.persistence.annotations.Entity;
import ru.itis.persistence.criteria.CriteriaBuilder;
import ru.itis.persistence.criteria.postgres.PostgresCriteriaBuilder;
import ru.itis.persistence.exceptions.NotPersistenceEntityException;

@Data
@Builder
public class Configuration {

    protected String url;
    protected String username;
    protected String password;
    protected Class driverClass;
    protected Dialect dialect;
    protected Integer maximumPoolSize;
    protected Boolean showSql;

    public CriteriaBuilder getCriteriaBuilder(){
        switch (dialect){
            case PostgreSQL9Dialect:
            default:
                return new PostgresCriteriaBuilder();
        }
    }

    public Registry registry(){
        return new Registry();
    }

    public class Registry{

        protected EntityManager entityManager;

        private Registry(){
            Integer customMaximumPoolSize = Configuration.this.getMaximumPoolSize();
            Configuration.this.setMaximumPoolSize(1);
            EntityManagerFactory entityManagerFactory = new EntityManagerFactory(Configuration.this);
            this.entityManager = entityManagerFactory.createEntityManager();
            Configuration.this.setMaximumPoolSize(customMaximumPoolSize);
        }

        public <T> Registry registerEntity(Class<T> entityClass){
            if (entityClass.getAnnotation(Entity.class) != null){
                entityManager.createTable(entityClass);
                return this;
            } else {
                throw new NotPersistenceEntityException("This class can't be registered, because it hasn't @Entity annotation.");
            }
        }

        public Configuration close(){
            entityManager.close();
            return Configuration.this;
        }
    }


}
