package org.cefet.repositories;

import org.cefet.models.User;

import java.util.Set;

public class UserRepository extends Repository<User> {
    public UserRepository(Set<User> database) {
        super(database);
    }

    public User findUserByEmail(String email) {
        return this.database.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
