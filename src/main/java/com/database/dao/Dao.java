package com.database.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    boolean remove(Long id);

    T getById(Long id);

    T add(T ob);

    Long update(T ob, Long id);

}
