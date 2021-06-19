package ru.itis.javalab.data.services;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();
    void delete(T t);
    T add(T t);
    T findById(ID id);
    T update(T t);

}
