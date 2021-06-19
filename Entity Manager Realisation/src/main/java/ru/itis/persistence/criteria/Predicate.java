package ru.itis.persistence.criteria;


public interface Predicate {

    Predicate create(Expression expression);
    Predicate merge(BooleanOperators operator, Expression expression);
    Predicate merge(BooleanOperators operator, Predicate predicate);
    Predicate bracket();
    Predicate negate();
    String getSql();

}
