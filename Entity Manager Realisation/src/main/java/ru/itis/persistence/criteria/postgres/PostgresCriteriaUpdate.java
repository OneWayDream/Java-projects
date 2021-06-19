package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.annotations.Column;
import ru.itis.persistence.annotations.Id;
import ru.itis.persistence.annotations.Transient;
import ru.itis.persistence.criteria.CriteriaUpdate;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.criteria.Predicate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class PostgresCriteriaUpdate<T> implements CriteriaUpdate<T> {

    protected StringBuilder query;
    protected List<String> setters;
    protected Predicate predicate;
    protected Class tClass;
    protected T entity;

    public PostgresCriteriaUpdate(T aClass){
        this.query = new StringBuilder("UPDATE ");
        tClass = aClass.getClass();
        setters = new ArrayList<>();
    }

    @Override
    public CriteriaUpdate<T> set(String attributeName, Object value) {
        setters.add(attributeName + "=" + value);
        return this;
    }

    @Override
    public CriteriaUpdate<T> where(Expression restriction) {
        predicate = new PostgresPredicate(restriction);
        return this;
    }

    @Override
    public CriteriaUpdate<T> where(Predicate restriction) {
        predicate = restriction;
        return this;
    }

    @Override
    public CriteriaUpdate<T> build() {
        query.append(PostgresUtils.getTableName(tClass))
                .append(" SET ");
        if (entity != null){
            for (Field field : entity.getClass().getDeclaredFields()){
                if (field.getAnnotation(Transient.class) == null){
                    int modifiersCode = field.getModifiers();
                    if ((!Modifier.isFinal(modifiersCode))&&(!Modifier.isStatic(modifiersCode))){
                        Column column = field.getAnnotation(Column.class);
                        Object result = PostgresUtils.getFieldValue(entity, field.getName(), field.getType());

                        if (field.getAnnotation(Id.class) !=null) {
                            continue;
                        } else {
                            query.append("\"")
                                    .append(((column != null)&&(!column.name().equals(""))) ? column.name() : PostgresUtils.generateSQLNameByFieldName(field.getName()))
                                    .append("\"=");
                        }

                        if (field.getType().getSimpleName().equals("String")) {
                            query.append("\'").append(result).append("\', ");
                        } else {
                            query.append(result).append(", ");
                        }
                    }
                }
            }
        } else {
            for (String setter : setters){
                query.append(setter).append(", ");
            }
        }
        query.deleteCharAt(query.length()-2);
        query.append("WHERE ")
                .append(predicate.getSql())
                .append(";");
        return this;
    }

    @Override
    public CriteriaUpdate<T> setAll(T t) {
        this.entity = t;
        return this;
    }

    @Override
    public String getSql() {
        return query.toString();
    }
}
