package ru.itis.repositories.minions_repositories.implementations;

import ru.itis.models.Item;
import ru.itis.repositories.RowMapper;
import ru.itis.repositories.SimpleJDBSTemplate;
import ru.itis.repositories.minions_repositories.interfaces.MinionsNPSPriceRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MinionsNPSPriceRepositoryImpl implements MinionsNPSPriceRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_ITEM = "SELECT * FROM item_nps_price WHERE item_name=?";
    //language=SQL
    private static final String SQL_INSERT_ENTRY = "INSERT INTO item_nps_price(item_name, price) VALUE (?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE item_nps_price SET price=? where item_name=?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM item_nps_price WHERE item_name=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM item_nps_price";



    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<Item> npsPriceRowMapper = row -> Item.builder()
            .itemName(row.getString("item_name").trim())
            .sellNPSPrice(row.getDouble("price"))
            .build();

    public MinionsNPSPriceRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }


    @Override
    public Optional<Item> getEntryByItemName(String itemName) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_ITEM, npsPriceRowMapper, itemName).stream().findAny();
    }

    @Override
    public void save(Item entity) {
        simpleJDBSTemplate.query(SQL_INSERT_ENTRY, npsPriceRowMapper, entity.getItemName(), entity.getSellNPSPrice());
    }

    @Override
    public void update(Item entity) {
        simpleJDBSTemplate.query(SQL_UPDATE, npsPriceRowMapper, entity.getSellNPSPrice(), entity.getItemName());
    }

    @Override
    public void delete(Item entity) {
        simpleJDBSTemplate.query(SQL_DELETE, npsPriceRowMapper, entity.getItemName());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.empty(); //not actually method for this database.
    }

    @Override
    public List<Item> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, npsPriceRowMapper);
    }
}
