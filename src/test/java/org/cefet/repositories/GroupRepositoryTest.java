package org.cefet.repositories;

import org.cefet.models.Group;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GroupRepositoryTest {
    private GroupRepository repository;
    private Set<Group> groupDatabase;

    @Before
    public void setUp() {
        this.groupDatabase = new HashSet<>();
        this.repository = new GroupRepository(groupDatabase);
    }

    @Test
    public void shouldSaveGroup() {
        repository.save(createGroup());

        assertEquals(1, groupDatabase.size());
    }

    @Test
    public void shouldDeleteGroup() {
        Group group = repository.save(createGroup());
        repository.delete(group);

        assertEquals(0, groupDatabase.size());
    }

    @Test
    public void shouldFindGroupById() {
        Group group = repository.save(createGroup());
        Group result = repository.findById(group.getId());

        assertEquals(group, result);
    }

    @Test
    public void shouldReturnNullWhenThereIsNotId() {
        repository.save(createGroup());
        Group result = repository.findById(999);

        assertNull(result);
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("Grupo Teste");
        group.setDate(new Date());

        return group;
    }
}
