package ru.java_school.mobile_operator.repository;

import java.util.List;

public interface GenericDao <E, K> {
    void add(E entity);
    void saveOrUpdate(E entity);
    void update(E entity);
    void remove(E entity);
    E find(K key);
    List<E> findAll();
}
