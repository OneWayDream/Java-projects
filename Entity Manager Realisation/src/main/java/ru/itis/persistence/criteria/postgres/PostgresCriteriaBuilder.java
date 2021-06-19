package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.*;

public class PostgresCriteriaBuilder implements CriteriaBuilder {


    @Override
    public CriteriaCreate createCriteriaCreate() {
        return new PostgresCriteriaCreate();
    }

    @Override
    public Object getFieldValue(Object entity, String fieldName, Class<?> fieldType) {
        return PostgresUtils.getFieldValue(entity, fieldName, fieldType);
    }

    @Override
    public <T> CriteriaQuery<T> createCriteriaQuery(Class<T> resultClass) {
        return new PostgresCriteriaQuery<T>(resultClass);
    }

    @Override
    public <T> CriteriaUpdate<T> createCriteriaUpdate(T targetEntity) {
        return new PostgresCriteriaUpdate<>(targetEntity);
    }

    @Override
    public <T> CriteriaDelete<T> createCriteriaDelete(Class<T> targetEntity) {
        return new PostgresCriteriaDelete<>(targetEntity);
    }

    @Override
    public <T> CriteriaSave<T> createCriteriaSave(T entity) {
        return new PostgresCriteriaSave<T>(entity);
    }

    @Override
    public <T> String getTableName(Class<T> target) {
        return PostgresUtils.getTableName(target);
    }

    @Override
    public Expression createExpression(String columnName, ConditionalSigns conditionalSigns, Object value) {
        return new PostgresExpression(columnName, conditionalSigns, value);
    }

    @Override
    public Predicate createPredicate(Expression expression) {
        return new PostgresPredicate(expression);
    }

    @Override
    public Selection createSelection(String columnName) {
        return new PostgresSelection(columnName);
    }

    @Override
    public Selection createSelection(String columnName, Integer order) {
        return new PostgresSelection(columnName, order);
    }

    @Override
    public Selection createSelection() {
        return PostgresSelection.DEFAULT();
    }
}
