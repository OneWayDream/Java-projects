package ru.itis.persistence.criteria;

public interface CriteriaUpdate<T> {

    CriteriaUpdate<T> set(String attributeName, Object value);

    CriteriaUpdate<T> where(Expression restriction);

    CriteriaUpdate<T> where(Predicate restriction);

    CriteriaUpdate<T> build();

    CriteriaUpdate<T> setAll(T t);

    String getSql();

}
