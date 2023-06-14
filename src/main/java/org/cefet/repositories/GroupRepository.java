package org.cefet.repositories;

import org.cefet.models.Group;

import java.util.Set;

public class GroupRepository extends Repository<Group> {
    public GroupRepository(Set<Group> database) {
        super(database);
    }
}
