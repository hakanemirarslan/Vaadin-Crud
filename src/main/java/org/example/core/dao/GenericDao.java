package org.example.core.dao;

import java.util.List;

public interface GenericDao<T> {

    /*T*/void save(T entity);
    T update(T entity);
    void deleteById(Long id);
    List<T> findAll();
    T findById(Long id);


}
