package org.cefet.services;

import org.cefet.enums.UserRole;
import org.cefet.models.Administrator;
import org.cefet.models.Group;
import org.cefet.models.Member;
import org.cefet.models.User;
import org.cefet.repositories.GroupRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class GroupServiceTest {
    private Set<Group> database;
    private GroupRepository groupRepository;
    private GroupService groupService;

    @Before
    public void setUp() {
        this.database = new HashSet<>();
        this.groupRepository = new GroupRepository(this.database);
        this.groupService = new GroupService(this.groupRepository);
    }

    @Test
    public void shouldAddUserInGroup() {
        User member = createMember();
        Group group = createGroup();

        this.groupService.addUser(member, group);

        assertTrue(group.getUsers().contains(member));
    }

    @Test
    public void shouldNotAddUserInGroup() {
        User member = createMember();
        Group group = createGroup();
        group.getUsers().add(member);

        this.groupService.addUser(member, group);

        assertEquals(1, group.getUsers().size());
    }

    @Test
    public void shouldRemoveUserFromGroup() {
        User member = createMember();
        Group group = createGroup();
        group.getUsers().add(member);

        this.groupService.removeUser(member, group);

        assertFalse(group.getUsers().contains(member));
        assertEquals(0, group.getUsers().size());
    }

    @Test
    public void shouldNotRemoveUserInGroup() {
        User member = createMember();
        Group group = createGroup();
        group.getUsers().add(member);

        User member_2 = createMember();
        member_2.setName("Sirius");

        this.groupService.removeUser(member_2, group);

        assertEquals(1, group.getUsers().size());
    }

    @Test
    public void shouldAddGroupToAdministrator() {
        Group group = createGroup();
        Administrator administrator = createAdministrator();

        this.groupService.createGroup(administrator, group);

        assertTrue(administrator.getGroups().contains(group));
    }

    @Test
    public void shouldNotAddGroupToAdministrator() {
        Group group = createGroup();
        Administrator administrator = createAdministrator();
        administrator.getGroups().add(group);

        this.groupService.createGroup(administrator, group);

        assertTrue(administrator.getGroups().contains(group));
        assertEquals(1, administrator.getGroups().size());
    }

    @Test
    public void shouldRemoveGroupFromAdministrator() {
        Group group = createGroup();
        Administrator administrator = createAdministrator();
        administrator.getGroups().add(group);

        this.groupService.deleteGroupFromAdministrator(administrator, group);

        assertEquals(0, administrator.getGroups().size());
    }

    @Test
    public void shouldNotRemoveGroupFromAdministrator() {
        Group group = createGroup();
        Administrator administrator = createAdministrator();
        administrator.getGroups().add(group);

        Group group_2 = createGroup();
        group_2.setName("Teste 2");

        this.groupService.deleteGroupFromAdministrator(administrator, group_2);

        assertTrue(administrator.getGroups().contains(group));
        assertEquals(1, administrator.getGroups().size());
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("Grupo Teste");
        group.setDate(new Date());

        return group;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Alvo");
        member.setEmail("member@teste");
        member.setLastName("Dumbledore");
        member.setPassword("123456");
        member.setUserRole(UserRole.BASIC);

        return member;
    }

    private Administrator createAdministrator() {
        Administrator administrator = new Administrator();
        administrator.setName("Severo");
        administrator.setLastName("Snape");
        administrator.setEmail("severo@adm");
        administrator.setPassword("456");
        administrator.setUserRole(UserRole.ADMINISTRATOR);

        return administrator;
    }
}
