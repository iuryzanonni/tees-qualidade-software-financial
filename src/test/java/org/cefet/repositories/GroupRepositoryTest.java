package org.cefet.repositories;

import org.cefet.models.Group;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GroupRepositoryTest {

    private Map<Integer, Group> groupDatabase;
    private GroupRepository repository;

    @Before
    public void setUp() {
        this.repository = new GroupRepository();
        groupDatabase = new HashMap<>();
    }

    @Test
    public void shouldSaveGroup() {
        repository.save(createGroup(), groupDatabase);

        assertEquals(1, groupDatabase.size());
    }

    @Test
    public void shouldDeleteGroup() {
        Group group = repository.save(createGroup(), groupDatabase);
        repository.delete(group, groupDatabase);

        assertEquals(0, groupDatabase.size());
    }

    @Test
    public void shouldFindGroupById() {
        Group group = repository.save(createGroup(), groupDatabase);
        Group result = repository.findById(group.getId(), groupDatabase);

        assertEquals(group, result);
    }

    @Test
    public void shouldReturnNullWhenThereIsNotId() {
        repository.save(createGroup(), groupDatabase);
        Group result = repository.findById(999, groupDatabase);

        assertNull(result);
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("Grupo Teste");
        group.setDate(new Date());

        return group;
    }
}
