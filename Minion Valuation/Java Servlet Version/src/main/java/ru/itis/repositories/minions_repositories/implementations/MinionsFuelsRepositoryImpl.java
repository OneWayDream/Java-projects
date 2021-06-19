package ru.itis.repositories.minions_repositories.implementations;

import ru.itis.models.Fuel;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsFuelsRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MinionsFuelsRepositoryImpl implements MinionsFuelsRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_FUEL_NAME = "SELECT * FROM fuels WHERE name=?";
    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO fuels(name, speed_boost) VALUE (?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE fuels SET speed_boost=? where name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM fuels WHERE name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM fuels";

    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<Fuel> fuelsRowMapper = row -> Fuel.builder()
            .name(row.getString("name").trim())
            .speedBoost(row.getInt("speed_boost"))
            .build();

    public MinionsFuelsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }

    @Override
    public void save(Fuel entity) {
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, fuelsRowMapper, entity.getName(), entity.getSpeedBoost());
    }

    @Override
    public void update(Fuel entity) {
        simpleJDBSTemplate.query(SQL_UPDATE, fuelsRowMapper, entity.getSpeedBoost(), entity.getName());
    }

    @Override
    public void delete(Fuel entity) {
        simpleJDBSTemplate.query(SQL_DELETE, fuelsRowMapper, entity.getName());
    }

    @Override
    public Optional<Fuel> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<Fuel> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, fuelsRowMapper);
    }


    @Override
    public Optional<Fuel> findByFuelName(String fuel_name) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_FUEL_NAME, fuelsRowMapper, fuel_name).stream().findAny();
    }
}
