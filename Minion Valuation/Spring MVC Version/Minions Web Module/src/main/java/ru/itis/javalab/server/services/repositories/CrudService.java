package ru.itis.javalab.server.services.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    List<T> findAll();
    void delete(T entity);
    T add(T entity);
    T findById(ID id);
    T update(T entity);
}
