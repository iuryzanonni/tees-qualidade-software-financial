package org.cefet.services;

import org.cefet.enums.UserRole;
import org.cefet.models.Member;
import org.cefet.models.User;
import org.cefet.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest {

    private static final String MEMBER_NAME = "Alvo";
    private static final String MEMBER_LAST_NAME = "Dumbledore";
    private static final String MEMBER_EMAIL = "alvo@member";
    private static final String MEMBER_PASSWORD = "abc123";
    private UserRepository userRepository;
    private UserService userService;
    private Set<User> database;

    @Before
    public void setUp() {
        this.database = new HashSet<>();
        this.userRepository = new UserRepository(this.database);
        this.userService = new UserService(this.userRepository);
    }

    @Test
    public void shouldCreateUser() {
        User user = this.userService.createUser(MEMBER_NAME, MEMBER_LAST_NAME, MEMBER_EMAIL, MEMBER_PASSWORD,
                UserRole.BASIC);

        assertTrue(database.contains(user));
    }

    @Test
    public void shouldDeleteUser() {
        User user = createMember();

        this.userService.deleteUser(user);

        assertEquals(0, database.size());
    }

    @Test
    public void shouldLoginUser() {
        User user = createMember();

        User userReturn = this.userService.login(MEMBER_EMAIL, MEMBER_PASSWORD);

        assertEquals(user, userReturn);
    }

    @Test
    public void shouldNotLoginUserWhenDontFindUser() {
        User user = createMember();

        User userReturn = this.userService.login("email@email", MEMBER_PASSWORD);

        assertNull(userReturn);
    }

    @Test
    public void shouldNotLoginUserWithWrongPassword() {
        User user = createMember();

        User userReturn = this.userService.login(MEMBER_EMAIL, "000");

        assertNull(userReturn);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName(MEMBER_NAME);
        member.setEmail(MEMBER_EMAIL);
        member.setLastName(MEMBER_LAST_NAME);
        member.setPassword(MEMBER_PASSWORD);
        member.setUserRole(UserRole.BASIC);

        this.userRepository.save(member);

        return member;
    }
}
