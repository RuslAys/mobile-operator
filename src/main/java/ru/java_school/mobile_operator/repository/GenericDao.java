package ru.java_school.mobile_operator.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(int id);
    List<T> findAll();
}
