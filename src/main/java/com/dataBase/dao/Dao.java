package com.dataBase.dao;

import com.model.Address;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    void remove(Long id);

    T getById(Long id);

     void add(T ob);

     Long update(T ob);

}
