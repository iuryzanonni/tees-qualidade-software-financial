package org.cefet.repositories;

import java.util.Map;

public interface IRepository<T> {
    T save(T t, Map<Integer, T> database);
    void delete(T t, Map<Integer, T> database);
    T findById(int id, Map<Integer, T> database);
}
