package ru.java_school.mobile_operator.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.java_school.mobile_operator.repository.GenericDao;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericDaoImpl <T extends Serializable>
    implements GenericDao<T> {

    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    public final void setClazz(Class<T> clazz){
        this.clazz = clazz;
    }

    public T save(T entity){
        return (T) getCurrentSession().save(entity);
    }

    public T update(T entity){
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity){
        getCurrentSession().delete(entity);
    }

    public T findById(int id){
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll(){
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
