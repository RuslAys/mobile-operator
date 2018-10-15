package ru.java_school.mobile_operator.service;

import java.util.List;

public interface GenericService<E, K> {
    void saveOrUpdate(E entity);
    List<E> findAll();
    E find(K id);
    void add(E entity);
    void update(E entity);
    void remove(E entity);
}
