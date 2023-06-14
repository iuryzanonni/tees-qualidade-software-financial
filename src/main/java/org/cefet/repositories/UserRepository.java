package org.cefet.repositories;

import org.cefet.models.User;

import java.util.Map;

public class UserRepository extends Repository<User>{
    public UserRepository(Map<Integer, User> database) {
        super(database);
    }
}
