package com.espark.adarsh.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GenericDao<E> {

    private List<E> store = new LinkedList<>();

    private Class<E> entityClass;

    public GenericDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public List<E> findAll() {
        return store;
    }

    public Optional<E> persist(E toPersist) {
        store.add(toPersist);
        return (Optional<E>) Optional.of(toPersist).orElse(null);
    }
}
