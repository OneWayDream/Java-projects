package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.ConditionalSigns;

public enum  PostgresConditionalSigns implements ConditionalSigns {

    EQUALS("="),
    MORE_THAN(">"),
    LESS_THAN("<"),
    MORE_OR_EQUALS_THAN(">="),
    LESS_OR_EQUALS_THAN("<="),
    IN("IN");

    protected String value;

    PostgresConditionalSigns(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
