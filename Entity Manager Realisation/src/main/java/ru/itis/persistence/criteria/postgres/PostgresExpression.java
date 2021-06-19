package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.criteria.ConditionalSigns;
import ru.itis.persistence.criteria.Expression;
import ru.itis.persistence.exceptions.ExpressionArgumentsException;

public class PostgresExpression implements Expression {

    protected String columnName;
    protected Object value;
    protected ConditionalSigns conditionalSigns;

    public PostgresExpression(String columnName, ConditionalSigns conditionalSigns , Object value){
        this.columnName = columnName;
        this.conditionalSigns = conditionalSigns;
        this.value = value;
    }

    @Override
    public String getSql() {
        StringBuilder result = new StringBuilder();
        result.append("\"").append(columnName).append("\" ").append(conditionalSigns.getValue()).append(" ");
        if (value.getClass().getSimpleName().equals("String")){
            result.append("\"").append(value).append("\"");
        } else {
            if (conditionalSigns.equals(PostgresConditionalSigns.IN)){
                try{
                    result.append(convertArrayToString((Object[]) value));
                } catch (ClassCastException ex){
                    throw new ExpressionArgumentsException("\"IN\" operator requires array object.");
                }
            } else {
                result.append(value);
            }
        }
        return result.toString();
    }

    protected String convertArrayToString(Object[] array){
        StringBuilder result = new StringBuilder("(");
        for (int i = 0; i<array.length; i++){
            result.append(array[i].toString()).append(", ");
        }
        result.deleteCharAt(result.length() - 1).deleteCharAt(result.length() - 1);
        result.append(")");
        return result.toString();
    }
}
