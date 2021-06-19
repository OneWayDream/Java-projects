package ru.itis.repositories.minions_repositories.implementations;

import ru.itis.models.Minion;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsRepository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MinionsRepositoryImpl implements MinionsRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_MINION_NAME = "SELECT * FROM minions WHERE name=?";
    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO minions(name, speed_1, speed_2, speed_3, speed_4, speed_5," +
            " speed_6, speed_7, speed_8, speed_9, speed_10, speed_11)  VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE minions SET speed_1=?, speed_2=?, speed_3=?, speed_4=?, speed_5=?," +
            "speed_6=?, speed_7=?, speed_8=?, speed_9=?, speed_10=?, speed_11=? where name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM minions WHERE name=?";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM minions WHERE name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM minions";



    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<Minion> minionRowMapper = row -> Minion.builder()
            .name(row.getString("name").trim())
            .timeBetweenActions(Arrays.asList(row.getDouble("speed_1"),
                    row.getDouble("speed_2"),
                    row.getDouble("speed_3"),
                    row.getDouble("speed_4"),
                    row.getDouble("speed_5"),
                    row.getDouble("speed_6"),
                    row.getDouble("speed_7"),
                    row.getDouble("speed_8"),
                    row.getDouble("speed_9"),
                    row.getDouble("speed_10"),
                    row.getDouble("speed_11")))
            .build();

    public MinionsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }

    @Override
    public Optional<Minion> getMinionByName(String minion_name) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_MINION_NAME, minionRowMapper, minion_name).stream().findAny();
    }

    @Override
    public void save(Minion entity) {
        List<Double> data = entity.getTimeBetweenActions();
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, minionRowMapper, entity.getName(), data.get(0), data.get(1), data.get(2),
                data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10));
    }

    @Override
    public void update(Minion entity) {
        List<Double> data = entity.getTimeBetweenActions();
        simpleJDBSTemplate.query(SQL_UPDATE, minionRowMapper, entity.getName(), data.get(0), data.get(1), data.get(2), data.get(3),
                data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), entity.getName());
    }

    @Override
    public void delete(Minion entity) {
        simpleJDBSTemplate.query(SQL_DELETE, minionRowMapper,entity.getName());
    }

    @Override
    public Optional<Minion> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<Minion> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, minionRowMapper);
    }
}
