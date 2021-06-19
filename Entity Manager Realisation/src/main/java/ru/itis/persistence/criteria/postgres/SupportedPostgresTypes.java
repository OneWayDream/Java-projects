package ru.itis.persistence.criteria.postgres;

public enum SupportedPostgresTypes {

    INTEGER,
    BIGINT,
    BOOLEAN,
    DOUBLE_PRECISION,
    REAL,
    CHARACTER_VARYING;

    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }
}
