package org.cefet.repositories;


import org.cefet.models.EntityBase;

import java.util.*;

public abstract class Repository<T extends EntityBase> implements IRepository<T> {

    protected Set<T> database;

    public Repository(Set<T> database) {
        this.database = database;
    }

    @Override
    public T save(T t) {
        int id = getNextKey();
        t.setId(id);
        t.setCreateDate(new Date());
        this.database.add(t);
        return t;
    }

    @Override
    public void delete(T t) {
        this.database.remove(t);
    }

    @Override
    public T findById(int id) {
        return this.database.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int getNextKey() {
        if (this.database.isEmpty()) {
            return 1;
        }

        int lastKey = this.database.stream()
                .max(Comparator.comparingInt(T::getId))
                .map(t -> t.getId())
                .orElse(0);

        return lastKey + 1;
    }

}
