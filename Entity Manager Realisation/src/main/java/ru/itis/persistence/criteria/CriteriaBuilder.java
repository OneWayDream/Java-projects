package ru.itis.persistence.criteria;



public interface CriteriaBuilder {

    CriteriaCreate createCriteriaCreate();

    Object getFieldValue(Object entity, String fieldName, Class<?> fieldType);

    <T> CriteriaQuery<T> createCriteriaQuery(Class<T> resultClass);

    <T> CriteriaUpdate<T> createCriteriaUpdate(T targetEntity);

    <T> CriteriaDelete<T> createCriteriaDelete(Class<T> targetEntity);

    <T> CriteriaSave<T> createCriteriaSave(T entity);

    <T> String getTableName(Class<T> target);

    Expression createExpression(String columnName, ConditionalSigns conditionalSigns , Object value);

    Predicate createPredicate(Expression expression);

    Selection createSelection(String columnName);

    Selection createSelection(String columnName, Integer order);

    Selection createSelection();


}
