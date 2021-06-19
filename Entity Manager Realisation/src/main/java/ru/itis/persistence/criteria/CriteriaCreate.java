package ru.itis.persistence.criteria;

public interface CriteriaCreate<T> {

    <T> CriteriaCreate createTable(Class<T> target);

    String getSql();

}
