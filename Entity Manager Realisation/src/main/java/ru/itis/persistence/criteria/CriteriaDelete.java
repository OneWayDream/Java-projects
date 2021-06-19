package ru.itis.persistence.criteria;

public interface CriteriaDelete<T> {

    CriteriaDelete<T> where(Expression restriction);

    CriteriaDelete<T> where(Predicate predicate);

    CriteriaDelete<T> build();

    String getSql();

}
