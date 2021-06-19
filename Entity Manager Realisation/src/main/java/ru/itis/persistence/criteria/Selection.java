package ru.itis.persistence.criteria;


//Что вытаскиваем
public interface Selection<T> {

    String  getColumnName();
    Integer getOrder();

}
