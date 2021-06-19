package ru.itis.persistence.criteria.postgres;

public enum  SupportedPostgresConstrains {

    PRIMARY_KEY, UNIQUE;

    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }
}
