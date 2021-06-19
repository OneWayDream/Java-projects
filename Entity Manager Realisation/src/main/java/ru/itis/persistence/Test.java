package ru.itis.persistence;

import ru.itis.persistence.core.Configuration;
import ru.itis.persistence.core.Dialect;
import ru.itis.persistence.core.EntityManager;
import ru.itis.persistence.core.EntityManagerFactory;
import ru.itis.persistence.criteria.CriteriaBuilder;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.criteria.SignsResolver;

public class Test {
    public static void main(String[] args) throws Exception {
        Configuration configuration = Configuration.builder()
                .url("jdbc:postgresql://localhost:5432/Minion_Valuation")
                .username("postgres")
                .password("Ki27082001rill")
                .driverClass(org.postgresql.Driver.class)
                .dialect(Dialect.PostgreSQL9Dialect)
                .maximumPoolSize(10)
                .showSql(true)
                .build()
                .registry()
                .registerEntity(Account.class)
                .close();
        EntityManagerFactory factory = new EntityManagerFactory(configuration);
        EntityManager entityManager = factory.createEntityManager();
//        entityManager.save(Account.builder()
//                     .id(1L)
//                     .name("Kirusha")
//                     .money(0)
//                     .transientField((short) 214)
//                     .build());
//                entityManager.save(Account.builder()
//                     .id(2L)
//                     .name("Admin")
//                     .money(9999999)
//                     .build());
//                entityManager.save(Account.builder()
//                     .id(3L)
//                     .name("User")
//                     .money(100)
//                     .build());
        SignsResolver signsResolver = factory.getSignsResolver();
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        System.out.println(entityManager.findById(Account.class, 1));
        System.out.println(entityManager.findAll(Account.class));
        Expression expression = builder.createExpression("money", signsResolver.getConditionalSign("<="),0);
        System.out.println(entityManager.findByPredicate(Account.class, builder.createPredicate(expression).bracket().negate().negate()));
        Expression expression1 = builder.createExpression("id", signsResolver.getConditionalSign("IN"), new Integer[]{1, 2});
        System.out.println(entityManager.findByPredicate(Account.class, builder.createPredicate(expression1)));
    }
}
