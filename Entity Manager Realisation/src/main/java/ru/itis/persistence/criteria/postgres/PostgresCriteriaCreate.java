package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.annotations.Column;
import ru.itis.persistence.annotations.Id;
import ru.itis.persistence.annotations.Table;
import ru.itis.persistence.annotations.Transient;
import ru.itis.persistence.criteria.CriteriaCreate;
import ru.itis.persistence.exceptions.IncorrectTableNameException;
import ru.itis.persistence.exceptions.NotIdFieldException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class PostgresCriteriaCreate<T> implements CriteriaCreate {

    protected StringBuilder query;

    public PostgresCriteriaCreate(){
        this.query = new StringBuilder("CREATE ");
    }

    @Override
    public CriteriaCreate createTable(Class target) {
        Arrays.stream(target.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny()
                .orElseThrow(() -> new NotIdFieldException("There is no id field, try to use @Id annotation."));

        query.append("TABLE IF NOT EXISTS ").append(PostgresUtils.getTableName(target)).append("(\n");

        Field[] fields = target.getDeclaredFields();
        for (Field field : fields){
            createColumnForField(field);
        }
        query.deleteCharAt(query.length() - 2);

        query.append(");");

        return this;
    }

    @Override
    public String getSql() {
        return query.toString();
    }

    protected void createColumnForField(Field field){
        if (field.getAnnotation(Transient.class) == null){
            int modifiersCode = field.getModifiers();
            if ((!Modifier.isFinal(modifiersCode))&&(!Modifier.isStatic(modifiersCode))){
                Column column = field.getAnnotation(Column.class);

                if (field.getAnnotation(Id.class) !=null) {
                    query.append("\"")
                            .append("id")
                            .append("\"");
                } else {
                    query.append("\"")
                            .append(((column != null)&&(!column.name().equals(""))) ? column.name() : PostgresUtils.generateSQLNameByFieldName(field.getName()))
                            .append("\"");
                }

                query.append(" ");

                query.append(PostgresUtils.getSQLTypeForJavaClass(field.getType().getSimpleName()).toString());

                if ((column != null)&&(column.unique())){
                    query.append(" ")
                            .append(SupportedPostgresConstrains.UNIQUE.toString());
                }

                if (field.getAnnotation(Id.class) !=null) {
                    query.append(" ")
                            .append(SupportedPostgresConstrains.PRIMARY_KEY.toString());
                }

                query.append(",\n");
            }
        }
    }
}
