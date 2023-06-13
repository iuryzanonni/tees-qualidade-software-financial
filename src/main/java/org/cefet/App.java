package org.cefet;

import org.cefet.models.Group;
import org.cefet.repositories.GroupRepository;

import java.util.HashMap;
import java.util.Map;

public class App
{
    public static void main( String[] args ) {

        System.out.println("##### Seja bem-vindo ao sistema financeiro #####");
        GroupRepository repository = new GroupRepository();
        Map<Integer, Group> groups = new HashMap<>();

        Group group = new Group();
        group.setName("Test 123");

        repository.save(group, groups);
        repository.save(group, groups);
    }
}
