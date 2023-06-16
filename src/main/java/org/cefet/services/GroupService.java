package org.cefet.services;

import org.cefet.models.Administrator;
import org.cefet.models.Group;
import org.cefet.models.User;
import org.cefet.repositories.GroupRepository;

import java.util.List;

public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addUser(User user, Group group) {
        List<User> users = group.getUsers();

        if (users.contains(user)) {
            System.out.println("Usuário existente no grupo.");
            return;
        }

        users.add(user);
        System.out.println("Usuário inserido com sucesso.");
    }

    public void removeUser(User user, Group group) {
        List<User> users = group.getUsers();

        if (!users.contains(user)) {
            System.out.println("Usuário não existente no grupo.");
            return;
        }

        users.remove(user);
        System.out.println("Usuário removido com sucesso.");
    }

    public void createGroup(Administrator administrator, Group group) {
        List<Group> groups = administrator.getGroups();

        if (groups.contains(group)) {
            System.out.printf("Grupo %s existente.%n", group.getName());
            return;
        }

        groups.add(group);
        System.out.println("Grupo criado com sucesso.");
    }

    public void deleteGroupFromAdministrator(Administrator administrator, Group group) {
        List<Group> groups = administrator.getGroups();

        if (!groups.contains(group)) {
            System.out.println("Grupo não encontrado.");
            return;
        }

        groups.remove(group);
        System.out.println("Grupo deletado com sucesso.");
    }
}
