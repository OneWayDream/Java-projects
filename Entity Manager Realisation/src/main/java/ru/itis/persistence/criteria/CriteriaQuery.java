package ru.itis.persistence.criteria;

import java.util.List;

public interface CriteriaQuery<T> {

    CriteriaQuery<T> select(Selection<? extends T> selection);

    CriteriaQuery<T> multiselect(Selection<? extends T>... selections);

    CriteriaQuery<T> multiselect(List<Selection<? extends T>> selectionList);

    CriteriaQuery<T> where(Expression restriction);

    CriteriaQuery<T> where(Predicate restriction);

    CriteriaQuery<T> build();

    String getSql();

}
