package ru.itis.persistence.core;

import io.zonky.test.db.postgres.embedded.DatabasePreparer;
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension;
import io.zonky.test.db.postgres.junit5.PreparedDbExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.persistence.Account;
import ru.itis.persistence.criteria.CriteriaBuilder;
import ru.itis.persistence.criteria.SignsResolver;
import ru.itis.persistence.criteria.postgres.PostgresCriteriaBuilder;
import ru.itis.persistence.criteria.postgres.PostgresSignsResolver;
import ru.itis.persistence.criteria.postgres.PostgresUtils;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.MatcherAssert.assertThat;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Entity Manager is working when:")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostgresEntityManagerTest {

    private final static CapturingDatabasePreparer preparer = new CapturingDatabasePreparer();
    protected static SignsResolver signsResolver;
    protected static CriteriaBuilder criteriaBuilder;
    protected static EntityManager entityManager;

    @RegisterExtension
    public static PreparedDbExtension db = EmbeddedPostgresExtension.preparedDatabase(preparer)
            .customize(builder -> builder.setConnectConfig("connectTimeout", "20"));

    //language=SQL
    private static final String SQL_SELECT_TABLE_INFO = "SELECT * FROM information_schema.tables WHERE table_name=?";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM \"generated_table\"";



    @BeforeAll
    public static void initAll(){
        signsResolver = new PostgresSignsResolver();
        criteriaBuilder = new PostgresCriteriaBuilder();
        Configuration configuration = Configuration.builder()
                .url("jdbc:postgresql://localhost:5432/Minion_Valuation")
                .username("postgres")
                .password("Ki27082001rill")
                .driverClass(org.postgresql.Driver.class)
                .dialect(Dialect.PostgreSQL9Dialect)
                .maximumPoolSize(10)
                .showSql(true)
                .build();

        EntityManagerFactory factory = new EntityManagerFactory(configuration);
        entityManager = factory.createEntityManager();
        entityManager.setDataSource(db.getTestDatabase());
        entityManager.setJdbcTemplate(new JdbcTemplate(db.getTestDatabase()));

        signsResolver = factory.getSignsResolver();
        criteriaBuilder = factory.getCriteriaBuilder();
    }

    @Test
    @Order(1)
    public void create_table() throws Exception{
        entityManager.createTable(Account.class);
        List<String> result= entityManager.getJdbcTemplate().query(SQL_SELECT_TABLE_INFO,
                (row, i) -> row.getString("table_name"),
                PostgresUtils.getTableName(Account.class));
        assertThat(result, is(not(empty())));
    }

    @Test
    @Order(2)
    public void save_entity() throws Exception{
        List<Account> data = data();
        for (Account account : data){
            entityManager.save(account);
        }
        List<Account> result= entityManager.getJdbcTemplate().query(SQL_FIND_ALL,
                (row, i) -> Account.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .money(row.getInt("money"))
                    .build()
        );

        assertThat(data, equalToObject(result));
    }

    @Test
    @Order(3)
    public void find_all() throws Exception{
        assertThat(entityManager.findAll(Account.class), equalToObject(data()));
    }

    @Test
    @Order(4)
    public void find_by_id() throws Exception{
        assertThat(entityManager.findById(Account.class, 2L),
                equalToObject(data().stream().filter(account -> account.getId()==2L).findAny().orElse(null)));
    }

    @Test
    @Order(5)
    public void find_by_id_list_predicate() throws Exception{
        assertThat(entityManager.findByPredicate(
                Account.class,
                    criteriaBuilder.createPredicate(
                        criteriaBuilder.createExpression(
                                "id", signsResolver.getConditionalSign("IN"), new Integer[]{1, 3}
                        )
                    )
                ),
                equalToObject(
                        data().stream().filter(account -> account.getId()==1L || account.getId() == 3L).collect(Collectors.toList())
                )
        );
    }

    @Test
    @Order(6)
    public void find_by_money_predicate() throws Exception{
        assertThat(entityManager.findByPredicate(
                Account.class,
                criteriaBuilder.createPredicate(
                        criteriaBuilder.createExpression(
                                "money", signsResolver.getConditionalSign(">"), 100
                        )
                )
                ),
                equalToObject(
                        data().stream().filter(account -> account.getMoney()>100).collect(Collectors.toList())
                )
        );
    }

    @Test
    @Order(7)
    public void update_by_id() throws Exception{
        List<Account> data = data();
        data.get(0).setName("HTML");
        entityManager.update(data.get(0));
        List<Account> database = entityManager.findAll(Account.class);
        database.sort(Comparator.comparing(Account::getId));
        assertThat(database, equalToObject(data));
    }

    @Test
    @Order(8)
    public void delete_by_id() throws Exception{
        List<Account> data = data();
        data.remove(0);
        entityManager.deleteById(Account.class, 1);
        List<Account> database = entityManager.findAll(Account.class);
        database.sort(Comparator.comparing(Account::getId));
        assertThat(database, equalToObject(data));
    }

    @Test
    @Order(9)
    public void delete_by_name_predicate() throws Exception{
        List<Account> data = new ArrayList<>();
        entityManager.delete(Account.class,
                    criteriaBuilder.createPredicate(
                            criteriaBuilder.createExpression(
                                    "money", signsResolver.getConditionalSign(">"), 100
                            )
                    )
                );
        List<Account> database = entityManager.findAll(Account.class);
        database.sort(Comparator.comparing(Account::getId));
        assertThat(database, equalToObject(data));
    }

    @AfterAll
    public static void tearDown(){
        entityManager.close();
    }

    private static class CapturingDatabasePreparer implements DatabasePreparer {

        private DataSource dataSource;

        @Override
        public void prepare(DataSource ds) {
            if (dataSource != null)
                throw new IllegalStateException("database preparer has been called multiple times");
            dataSource = ds;
        }

        public DataSource getDataSource() {
            return dataSource;
        }
    }

    protected static List<Account> data(){
        List<Account> result= new ArrayList<>();
        result.add(Account.builder().id(1L).name("JSON").money(100).build());
        result.add(Account.builder().id(2L).name("YAML").money(200).build());
        result.add(Account.builder().id(3L).name("CSV").money(300).build());
        return result;
    }

}
