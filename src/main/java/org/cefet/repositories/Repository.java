package org.cefet.repositories;


import org.cefet.models.EntityBase;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Repository<T extends EntityBase> implements IRepository<T> {

    private Map<Integer, T> database;

    public Repository(Map<Integer, T> database) {
        this.database = database;
    }

    @Override
    public T save(T t) {
        int id = getNextKey();
        t.setId(id);
        t.setCreateDate(new Date());
        this.database.put(id, t);
        return t;
    }

    @Override
    public void delete(T t) {
        this.database.remove(t.getId());
    }

    @Override
    public T findById(int id) {
        return this.database.get(id);
    }

    private int getNextKey() {
        if (this.database.isEmpty()) {
            return 1;
        }
        SortedMap<Integer, T> mapSorted = new TreeMap<>(this.database);
        return mapSorted.lastKey() + 1;
    }
}
