package ru.itis.persistence.criteria.postgres;

import ru.itis.persistence.annotations.Table;
import ru.itis.persistence.exceptions.IncorrectTableNameException;
import ru.itis.persistence.exceptions.NoGetMethodForFieldException;
import ru.itis.persistence.exceptions.EntityFormatException;
import ru.itis.persistence.exceptions.UnsupportedTypeException;

import java.lang.reflect.Method;

public class PostgresUtils {


    public static SupportedPostgresTypes getSQLTypeForJavaClass(String className){
        SupportedPostgresTypes result = null;
        switch (className) {
            case "byte":
            case "short":
            case "int":
            case "Byte":
            case "Short":
            case "Integer":
                result = SupportedPostgresTypes.INTEGER;
                break;
            case "long":
            case "Long":
                result = SupportedPostgresTypes.BIGINT;
                break;
            case "boolean":
            case "Boolean":
                result = SupportedPostgresTypes.BOOLEAN;
                break;
            case "float":
            case "Float":
                result = SupportedPostgresTypes.DOUBLE_PRECISION;
                break;
            case "double":
            case "Double":
                result = SupportedPostgresTypes.REAL;
                break;
            case "String":
                result = SupportedPostgresTypes.CHARACTER_VARYING;
                break;
            default:
                throw new UnsupportedTypeException("Lol, its really weak lib, u can't use " + className + " type.");

        }
        return result;
    }

    public static <T> String getTableName(Class<T> target){
        Table table = target.getAnnotation(Table.class);
        String annotationName = (table == null) ? "" : table.name();
        if (isCorrectName(annotationName)){
            return (annotationName.equals("")) ? getNameByClassName(target.getSimpleName()) : annotationName;
        } else {
            throw new IncorrectTableNameException("Incorrect custom table name (try to not use uppercase or spaces)");
        }
    }

    public static String generateSQLNameByFieldName(String fieldName){
        return fieldName.toLowerCase();
    }

    public static Object getFieldValue(Object entity, String fieldName, Class<?> fieldType){
        Method[] methods = entity.getClass().getDeclaredMethods();
        String methodName = "get" + fieldName;
        methodName = methodName.toLowerCase();
        Object result = null;
        boolean isValue = false;
        for (Method method : methods) {
            if ((method.getName().toLowerCase().equals(methodName)) && (method.getReturnType().equals(fieldType))) {
                try{
                    result = method.invoke(entity);
                } catch (Exception ex){
                    throw new EntityFormatException("Something went wrong trying to get field. Check your getter methods.", ex);
                }
                isValue = true;
            }
        }
        if (isValue) {
            return result;
        } else {
            throw new NoGetMethodForFieldException("There is no getter for " + fieldName + " field.");
        }
    }

    private static boolean isCorrectName(String name){
        return ((name.equals(name.toLowerCase()))&&(!name.contains(" ")));
    }

    private static String getNameByClassName(String className){
        return className.toLowerCase();
    }

}
