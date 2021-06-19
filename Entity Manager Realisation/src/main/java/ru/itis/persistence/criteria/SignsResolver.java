package ru.itis.persistence.criteria;


public interface SignsResolver {

    BooleanOperators getBooleanOperator(String operator);
    ConditionalSigns getConditionalSign(String sign);

}
