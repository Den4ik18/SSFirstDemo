package com.database.service;

import java.util.List;

public interface Service<T> {
    List<T> getAll();

    boolean remove(Long id);

    T getById(Long id);

    T add(T ob);

    Long update(T ob, Long id);

    boolean removeByParameter(String parameter);
}
