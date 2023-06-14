package org.cefet.repositories;


import org.cefet.models.EntityBase;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Repository<T extends EntityBase> implements IRepository<T> {

    @Override
    public T save(T t, Map<Integer, T> database) {
        int id = getNextKey(database);
        t.setId(id);
        t.setCreateDate(new Date());
        database.put(id, t);
        return t;
    }

    @Override
    public void delete(T t, Map<Integer, T> database) {
        database.remove(t.getId());
    }

    @Override
    public T findById(int id, Map<Integer, T> database) {
        return database.get(id);
    }

    private int getNextKey(Map<Integer, T> database) {
        if (database.isEmpty()) {
            return 1;
        }
        SortedMap<Integer, T> mapSorted = new TreeMap<>(database);
        return mapSorted.lastKey() + 1;
    }
}
