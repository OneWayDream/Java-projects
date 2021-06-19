package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.Selection;

public class PostgresSelection<T> implements Selection<T> {

    protected Integer order;
    protected String columnName;

    public static PostgresSelection DEFAULT(){
        return new PostgresSelection("*");
    }

    public PostgresSelection(String columnName){
        this.columnName = columnName;
        this.order = -1;
    }

    public PostgresSelection(String columnName, Integer order){
        this.columnName = columnName;
        this.order = order;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public Integer getOrder() {
        return order;
    }

}
