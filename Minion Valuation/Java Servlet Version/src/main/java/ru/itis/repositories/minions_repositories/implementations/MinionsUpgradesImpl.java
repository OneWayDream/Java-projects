package ru.itis.repositories.minions_repositories.implementations;

import javafx.util.Pair;
import ru.itis.models.Upgrade;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsUpgrades;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MinionsUpgradesImpl implements MinionsUpgrades {

    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO upgrades(name, upgrade_group)  " +
            "VALUE (?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE upgrades SET upgrade_group=? WHERE name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM upgrades WHERE name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM upgrades";



    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<Upgrade> upgradesRowMapper = row -> Upgrade.builder()
            .name(row.getString("name").trim())
            .groupName(row.getString("upgrade_group").trim())
            .build();

    public MinionsUpgradesImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }

    @Override
    public void save(Upgrade entity) {
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, upgradesRowMapper, entity.getName(), entity.getGroupName());
    }

    @Override
    public void update(Upgrade entity) {
        simpleJDBSTemplate.query(SQL_UPDATE, upgradesRowMapper, entity.getGroupName(), entity.getName());
    }

    @Override
    public void delete(Upgrade entity) {
        simpleJDBSTemplate.query(SQL_DELETE, upgradesRowMapper, entity.getName());
    }

    @Override
    public Optional<Upgrade> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<Upgrade> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, upgradesRowMapper);
    }
}
