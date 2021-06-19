package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.BooleanOperators;

public enum PostgresBooleanOperators implements BooleanOperators {

    AND("AND"),
    OR("OR"),
    NOT("NOT");

    protected String value;

    PostgresBooleanOperators(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
