package ru.itis.repositories.minions_repositories.implementations;

import ru.itis.models.Item;
import ru.itis.models.Production;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsProductionRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MinionsProductionRepositoryImpl implements MinionsProductionRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_MINION_NAME = "SELECT * FROM production WHERE minion_name=?";
    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO production(minion_name, item_name, chance, amount)  VALUE (?,?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE production SET item_name=?, chance=?, amount=? WHERE minion_name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM production WHERE minion_name=? AND item_name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM production";



    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<Production> productionRowMapper = row -> Production.builder()
            .minionName(row.getString("minion_name").trim())
            .itemName(row.getString("item_name").trim())
            .chance(row.getInt("chance"))
            .amount(row.getDouble("amount"))
            .build();

    public MinionsProductionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }


    @Override
    public List<Production> getProductionsByMinionName(String minion_name) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_MINION_NAME, productionRowMapper, minion_name);
    }

    @Override
    public void save(Production entity) {
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, productionRowMapper, entity.getMinionName(), entity.getItemName(),
                entity.getChance(), entity.getAmount());
    }

    @Override
    public void update(Production entity) {
        simpleJDBSTemplate.query(SQL_UPDATE, productionRowMapper, entity.getItemName(), entity.getChance(),
                entity.getAmount(), entity.getMinionName());
    }

    @Override
    public void delete(Production entity) {
        simpleJDBSTemplate.query(SQL_DELETE, productionRowMapper, entity.getMinionName(), entity.getItemName());
    }

    @Override
    public Optional<Production> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<Production> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, productionRowMapper);
    }
}
