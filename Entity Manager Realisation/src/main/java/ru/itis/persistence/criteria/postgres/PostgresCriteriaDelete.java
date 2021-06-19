package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.CriteriaDelete;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.criteria.Predicate;

public class PostgresCriteriaDelete<T> implements CriteriaDelete<T> {

    protected StringBuilder query;
    protected Predicate predicate;
    protected Class<T> tClass;

    public PostgresCriteriaDelete(Class<T> targetClass){
        this.query = new StringBuilder("DELETE FROM ");
        this.tClass = targetClass;
    }

    @Override
    public CriteriaDelete<T> where(Expression restriction) {
        predicate = new PostgresPredicate(restriction);
        return this;
    }

    @Override
    public CriteriaDelete<T> where(Predicate restriction) {
        predicate = restriction;
        return this;
    }

    @Override
    public CriteriaDelete<T> build() {
        query.append("\"").append(PostgresUtils.getTableName(tClass))
                .append("\"")
                .append(" WHERE ")
                .append(predicate.getSql())
                .append(";");
        return this;
    }

    @Override
    public String getSql() {
        return query.toString();
    }
}
