package com.div.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAO<T, ID extends Serializable> {

    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findOne(ID id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName(), entityClass).list();
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void deleteById(ID entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}