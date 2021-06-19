package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.BooleanOperators;
import ru.itis.persistence.criteria.ConditionalSigns;
import ru.itis.persistence.criteria.SignsResolver;
import ru.itis.persistence.exceptions.UnknownBooleanOperatorException;
import ru.itis.persistence.exceptions.UnknownConditionalSignException;

public class PostgresSignsResolver implements SignsResolver {

    @Override
    public BooleanOperators getBooleanOperator(String operator) {
        BooleanOperators result;
        operator = operator.toUpperCase();
        switch (operator){
            case "AND":
                result = PostgresBooleanOperators.AND;
                break;
            case "OR":
                result = PostgresBooleanOperators.OR;
                break;
            case "NOT":
                result = PostgresBooleanOperators.NOT;
            default:
                throw new UnknownBooleanOperatorException("Cannot resolve \"" + operator + "\" operator. " +
                        "Check " + PostgresBooleanOperators.class.getSimpleName() + " to look through all supported operators.");
        }
        return result;
    }

    @Override
    public ConditionalSigns getConditionalSign(String sign) {
        ConditionalSigns result;
        sign = sign.toUpperCase();
        switch (sign){
            case "=":
                result = PostgresConditionalSigns.EQUALS;
                break;
            case ">":
                result = PostgresConditionalSigns.MORE_THAN;
                break;
            case "<":
                result = PostgresConditionalSigns.LESS_THAN;
                break;
            case ">=":
                result = PostgresConditionalSigns.MORE_OR_EQUALS_THAN;
                break;
            case "<=":
                result = PostgresConditionalSigns.LESS_OR_EQUALS_THAN;
                break;
            case "IN":
                result = PostgresConditionalSigns.IN;
                break;
            default:
                throw new UnknownConditionalSignException("Cannot resolve \"" + sign + "\" sign. " +
                        "Check " + PostgresConditionalSigns.class.getSimpleName() + " to look through all supported signs.");
        }
        return result;
    }
}
