package org.cefet.repositories;

import org.cefet.models.Group;

import java.util.Map;

public class GroupRepository extends Repository<Group> {
    public GroupRepository(Map<Integer, Group> database) {
        super(database);
    }
}
