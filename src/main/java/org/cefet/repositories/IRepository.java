package org.cefet.repositories;

import java.util.Map;

public interface IRepository<T> {
    T save(T t);
    void delete(T t);
    T findById(int id);
}
