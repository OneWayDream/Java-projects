package ru.itis.repositories.minions_repositories.implementations;

import ru.itis.models.ItemAction;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsItemSuperCompactRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MinionsItemSuperCompactRepositoryImpl implements MinionsItemSuperCompactRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_ITEM_NAME = "SELECT * FROM item_super_compact WHERE item_name=?";
    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO item_super_compact(item_name, result_name, in_amount, out_amount)  VALUE (?,?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE item_super_compact SET result_name=?, in_amount=?, out_amount=? where item_name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM item_super_compact WHERE item_name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM item_super_compact";


    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<ItemAction> superCompactRowMapper = row -> ItemAction.builder()
            .itemName(row.getString("item_name").trim())
            .resultName(row.getString("result_name").trim())
            .inAmount(row.getInt("in_amount"))
            .outAmount(row.getInt("out_amount"))
            .build();

    public MinionsItemSuperCompactRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }

    @Override
    public Optional<ItemAction> findItemSuperCompactByItemName(String itemName) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_ITEM_NAME, superCompactRowMapper, itemName).stream().findAny();
    }

    @Override
    public void save(ItemAction entity) {
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, superCompactRowMapper, entity.getItemName(), entity.getResultName(),
                entity.getInAmount(), entity.getOutAmount());
    }

    @Override
    public void update(ItemAction entity) {
        simpleJDBSTemplate.query(SQL_UPDATE, superCompactRowMapper, entity.getResultName(), entity.getInAmount(),
                entity.getOutAmount(), entity.getItemName());
    }

    @Override
    public void delete(ItemAction entity) {
        simpleJDBSTemplate.query(SQL_DELETE, superCompactRowMapper, entity.getItemName());
    }

    @Override
    public Optional<ItemAction> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<ItemAction> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, superCompactRowMapper);
    }
}
