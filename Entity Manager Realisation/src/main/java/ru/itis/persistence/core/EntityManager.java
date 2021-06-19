package ru.itis.persistence.core;

import com.mchange.v2.c3p0.DataSources;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.persistence.annotations.Column;
import ru.itis.persistence.annotations.Id;
import ru.itis.persistence.criteria.*;
import ru.itis.persistence.exceptions.NoSetMethodForFieldException;
import ru.itis.persistence.exceptions.NoSuchEntityException;
import ru.itis.persistence.exceptions.QueryException;
import ru.itis.persistence.exceptions.EntityFormatException;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EntityManager {

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;
    protected CriteriaBuilder criteriaBuilder;
    protected Boolean showSql;
    protected SignsResolver signsResolver;

    //language=SQL
    protected static final String SQL_GET_COLUMNS_INFO_FOR_TABLE = "SELECT TABLE_NAME, COLUMN_NAME, data_type FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name=?";

    protected RowMapper<TableInfo> tableInfoRowMapper = (row, i) -> TableInfo.builder()
            .tableName(row.getString("table_name"))
            .columnName(row.getString("column_name"))
            .dataType(row.getString("data_type"))
            .build();

    public EntityManager (DataSource dataSource, CriteriaBuilder criteriaBuilder, Boolean showSql, SignsResolver signsResolver){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.criteriaBuilder = criteriaBuilder;
        this.showSql = showSql;
        this.signsResolver = signsResolver;
    }

    public <T> void createTable (Class<T> entityClass){
        CriteriaCreate criteriaCreate = criteriaBuilder.createCriteriaCreate()
                .createTable(entityClass);
        if (showSql){
            System.out.println(criteriaCreate.getSql());
        }
        jdbcTemplate.execute(criteriaCreate.getSql());
    }

    public <T> void save (T entity){
        CriteriaSave<T> criteriaSave = criteriaBuilder.createCriteriaSave(entity);
        if (showSql){
            System.out.println(criteriaSave.getSql());
        }
        jdbcTemplate.update(criteriaSave.getSql());
    }

    public <T> void delete (Class<T> targetClass, Predicate predicate){
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(targetClass)
                .where(predicate)
                .build();
        if (showSql){
            System.out.println(criteriaDelete.getSql());
        }
        jdbcTemplate.update(criteriaDelete.getSql());
    }

    public <T> void update (T entity){
        Field idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class)!=null)
                .findAny()
                .orElseThrow(EntityFormatException::new);
        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(entity)
                .setAll(entity)
                .where(
                        criteriaBuilder.createExpression(
                                "id",
                                signsResolver.getConditionalSign("="),
                                criteriaBuilder.getFieldValue(entity, idField.getName(), idField.getType())
                        )
                )
                .build();
        if (showSql){
            System.out.println(criteriaUpdate.getSql());
        }
        jdbcTemplate.update(criteriaUpdate.getSql());
    }

    public <T, ID> void deleteById (Class<T> targetClass, ID id){
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(targetClass)
                .where(criteriaBuilder.createExpression("id", signsResolver.getConditionalSign("="), id))
                .build();
        if (showSql){
            System.out.println(criteriaDelete.getSql());
        }
        jdbcTemplate.update(criteriaDelete.getSql());
    }

    public <T, ID> T findById(Class<T> resultType, ID idValue){
        CriteriaQuery criteriaQuery = criteriaBuilder
                .createCriteriaQuery(resultType)
                .where(criteriaBuilder.createExpression("id", signsResolver.getConditionalSign("=") ,idValue))
                .select(criteriaBuilder.createSelection())
                .build();
        if (showSql){
            System.out.println(criteriaQuery.getSql());
        }
        return executeSelect(criteriaQuery.getSql(), resultType).stream()
                .findAny()
                .orElseThrow(() -> new NoSuchEntityException("There is no entity with such id."));
    }

    public <T> List<T> findAll(Class<T> resultType){
        CriteriaQuery criteriaQuery = criteriaBuilder
                .createCriteriaQuery(resultType)
                .select(criteriaBuilder.createSelection())
                .build();
        if (showSql){
            System.out.println(criteriaQuery.getSql());
        }
        return new ArrayList<>(executeSelect(criteriaQuery.getSql(), resultType));
    }

    public <T> List<T> findByPredicate(Class<T> resultType, Predicate predicate){
        CriteriaQuery criteriaQuery = criteriaBuilder
                .createCriteriaQuery(resultType)
                .where(predicate)
                .select(criteriaBuilder.createSelection())
                .build();
        if (showSql){
            System.out.println(criteriaQuery.getSql());
        }
        return new ArrayList<>(executeSelect(criteriaQuery.getSql(), resultType));
    }

    protected <T> List<T> executeSelect(String sql, Class<T> resultType){
        try{
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            List<FieldUnit> fieldUnits = Arrays.stream(resultType.getDeclaredFields())
                    .map(field -> FieldUnit.builder().field(field).columnName(getNameForField(field)).build())
                    .collect(Collectors.toList());
            List<T> result = new ArrayList<>();
            List<TableInfo> tableInfos = getSQLFieldsForTable(criteriaBuilder.getTableName(resultType));


            connection = this.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()){
                    T entity = resultType.newInstance();
                    for (TableInfo tableInfo : tableInfos){

                        FieldUnit fieldUnit = fieldUnits.stream()
                                .filter(unit -> unit.getColumnName().equals(tableInfo.getColumnName()))
                                .findAny()
                                .orElse(null);
                        if (fieldUnit != null) {
//                            fieldUnit.getField().setAccessible(true);
//                            try{
//                                fieldUnit.getField().set(entity, resultSet.getObject(tableInfo.getColumnName()));
//                            } catch (SQLException ex){
//                                //ignore
//                            } finally {
//                                fieldUnit.getField().setAccessible(false);
//                            }
                            String methodName = "set" + fieldUnit.getField().getName().substring(0, 1).toUpperCase() + fieldUnit.getField().getName().substring(1);
                            try{
                                Method method = resultType.getMethod(methodName, fieldUnit.getField().getType());
                                method.invoke(entity, resultSet.getObject(tableInfo.getColumnName()));
                            } catch (NoSuchMethodException ex) {
                                throw new NoSetMethodForFieldException("Here is no setter for " + fieldUnit.getField().getName() +
                                        " in " + resultType.getSimpleName() + " (try to find " + methodName + ").", ex);
                            } catch (InvocationTargetException ex) {
                                throw new EntityFormatException(ex);
                            }
                        }

                    }
                    result.add(entity);
                }
            }

            return result;

        } catch (SQLException ex) {
            throw new QueryException(ex);
        } catch (IllegalAccessException | InstantiationException ex) {
            throw new EntityFormatException(ex);
        }
    };

    public void close(){
        try{
            DataSources.destroy(dataSource);
        } catch (Exception ex){
            //ignore
        }
    }

    protected List<TableInfo> getSQLFieldsForTable(String tableName) {
        return jdbcTemplate.query(SQL_GET_COLUMNS_INFO_FOR_TABLE, tableInfoRowMapper, tableName);
    }

    protected String getNameForField(Field field){
        String result = null;
        if (field.getAnnotation(Id.class) != null){
            result = "id";
        } else {
            Column column = field.getAnnotation(Column.class);
            result = ((column != null) && (!column.name().equals(""))) ? column.name() : field.getName();
        }
        return result;
    }

}
