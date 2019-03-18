package com.database.service;


public interface AdditionalService<T> {
    boolean removeByParameter(String parameter);
    void addObjectForEmployee(T ob, Long id);
}
