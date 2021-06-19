package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.annotations.Column;
import ru.itis.persistence.annotations.Id;
import ru.itis.persistence.annotations.Transient;
import ru.itis.persistence.criteria.CriteriaSave;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PostgresCriteriaSave<T> implements CriteriaSave<T> {

    protected StringBuilder query;
    protected StringBuilder parameters;

    public PostgresCriteriaSave(T entity){
        this.query = new StringBuilder("INSERT INTO ");
        this.parameters = new StringBuilder("(");
        saveEntity(entity);
    }

    protected void saveEntity(T entity){

        String tableName = PostgresUtils.getTableName(entity.getClass());
        query.append("\"")
                .append(tableName)
                .append("\"");

        query.append(" (");

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields){
            saveField(field, entity);
        }

        query.deleteCharAt(query.length() - 1).deleteCharAt(query.length() - 1);
        parameters.deleteCharAt(parameters.length() - 1).deleteCharAt(parameters.length() - 1).append(");");
        query.append(") VALUES ").append(parameters.toString());
    }

    protected void saveField(Field field, T entity){
        if (field.getAnnotation(Transient.class) == null){
            int modifiersCode = field.getModifiers();
            if ((!Modifier.isFinal(modifiersCode))&&(!Modifier.isStatic(modifiersCode))){
                Column column = field.getAnnotation(Column.class);
                Object result = PostgresUtils.getFieldValue(entity, field.getName(), field.getType());

                if (field.getAnnotation(Id.class) !=null) {
                    query.append("id")
                            .append(", ");
                } else {
                    query.append(((column != null)&&(!column.name().equals(""))) ? column.name() : PostgresUtils.generateSQLNameByFieldName(field.getName()))
                            .append(", ");
                }

                if (field.getType().getSimpleName().equals("String")) {
                    parameters.append("\'").append(result).append("\', ");
                } else {
                    parameters.append(result).append(", ");
                }
            }
        }
    }

    @Override
    public String getSql() {
        return query.toString();
    }

}
