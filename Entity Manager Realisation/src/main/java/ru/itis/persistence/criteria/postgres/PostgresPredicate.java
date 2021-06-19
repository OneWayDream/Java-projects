package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.BooleanOperators;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.criteria.Predicate;

public class PostgresPredicate implements Predicate {

    protected StringBuilder sql;

    public PostgresPredicate(Expression expression){
        this.sql = new StringBuilder(expression.getSql());
    }

    @Override
    public Predicate create(Expression expression) {
        this.sql = new StringBuilder(expression.getSql());
        return this;
    }

    @Override
    public Predicate merge(BooleanOperators operator, Expression expression) {
        sql.append(operator.getValue()).append(expression.getSql());
        return this;
    }

    @Override
    public Predicate merge(BooleanOperators operator, Predicate predicate) {
        sql.append(operator.getValue()).append(predicate.getSql());
        return this;
    }

    @Override
    public Predicate bracket() {
        String oldSql = sql.toString();
        this.sql = new StringBuilder();
        sql.append("(").append(oldSql).append(")");
        return this;
    }

    @Override
    public Predicate negate() {
        String oldSql = sql.toString();
        this.sql = new StringBuilder();
        sql.append(PostgresBooleanOperators.NOT.getValue()).append(" ").append(oldSql);
        return this;
    }

    @Override
    public String getSql() {
        return sql.toString();
    }
}
