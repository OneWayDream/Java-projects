package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.CriteriaQuery;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.criteria.Predicate;
import ru.itis.persistence.criteria.Selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostgresCriteriaQuery<T> implements CriteriaQuery<T> {

    protected StringBuilder query;
    protected List<Selection<? extends T>> selections;
    protected Predicate predicate;
    protected Class<T> tClass;

    public PostgresCriteriaQuery(Class<T> resultClass){
        this.query = new StringBuilder("SELECT ");
        this.selections = new ArrayList<>();
        this.tClass = resultClass;
    }

    @Override
    public CriteriaQuery<T> select(Selection<? extends T> selection) {
        selections.add(selection);
        return this;
    }

    @Override
    public CriteriaQuery<T> where(Expression restriction) {
        predicate = new PostgresPredicate(restriction);
        return this;
    }

    @Override
    public CriteriaQuery<T> where(Predicate restriction) {
        predicate = restriction;
        return this;
    }

    @Override
    public CriteriaQuery<T> build() {
        if (selections.size()==0){
            Selection selection = PostgresSelection.DEFAULT();
            query.append(selection.getColumnName()).append(" ");
        } else {
            selections = selections.stream().sorted((Comparator.comparing(Selection::getOrder))).collect(Collectors.toList());
            for (Selection selection : selections){
                query.append(selection.getColumnName()).append(", ");
            }
            query.deleteCharAt(query.length() - 1).deleteCharAt(query.length() - 1);
        }

        query.append(" FROM ")
                .append("\"").append(PostgresUtils.getTableName(tClass)).append("\"");

        if (predicate !=null){
            query.append(" WHERE ")
                    .append(predicate.getSql());
        }

        query.append(";");
        return this;
    }

    @Override
    public String getSql() {
        return query.toString();
    }

    @Override
    public CriteriaQuery<T> multiselect(List<Selection<? extends T>> list) {
        this.selections = list;
        return this;
    }

    @Override
    public CriteriaQuery<T> multiselect(Selection<? extends T>[] selections) {
        this.selections = Arrays.asList(selections);
        return this;
    }
}
