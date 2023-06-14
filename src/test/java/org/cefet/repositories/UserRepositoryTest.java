package org.cefet.repositories;

import org.cefet.enums.UserRole;
import org.cefet.models.Administrator;
import org.cefet.models.Member;
import org.cefet.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {
    private Set<User> userDatabase;
    private UserRepository repository;

    @Before
    public void setUp() {
        this.userDatabase = new HashSet<>();
        this.repository = new UserRepository(this.userDatabase);
    }

    @Test
    public void shouldSaveMember() {
        Member member = createMember();
        repository.save(member);

        assertEquals(1, userDatabase.size());
    }

    @Test
    public void shouldSaveAdministrator() {
        Administrator administrator = createAdministrator();
        repository.save(administrator);

        assertEquals(1, userDatabase.size());
    }

    @Test
    public void shouldSaveMultipleUsers() {
        Member member = createMember();
        Administrator administrator = createAdministrator();

        repository.save(member);
        repository.save(administrator);

        assertEquals(2, userDatabase.size());
    }

    @Test
    public void shouldFindUserById() {
        Member member = createMember();
        Administrator administrator = createAdministrator();

        repository.save(member);
        repository.save(administrator);

        User user = repository.findById(2);

        assertEquals(administrator, user);
    }

    @Test
    public void shouldDeleteUser() {
        Member member = createMember();
        Administrator administrator = createAdministrator();

        repository.save(member);
        repository.save(administrator);

        repository.delete(administrator);

        assertEquals(1, userDatabase.size());
    }

    @Test
    public void shouldFindUserByEmail() {
        Member member = createMember();
        Administrator administrator = createAdministrator();

        repository.save(member);
        repository.save(administrator);

        User user = repository.findUserByEmail(administrator.getEmail());

        assertEquals(administrator, user);
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
