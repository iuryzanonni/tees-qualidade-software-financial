package org.cefet.services;

import org.cefet.enums.UserRole;
import org.cefet.models.Member;
import org.cefet.models.User;
import org.cefet.repositories.UserRepository;

import java.util.ArrayList;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String lastName, String email, String password, UserRole userRole) {
        Member member = new Member(name, lastName, email, password, UserRole.BASIC, new ArrayList<>());

        try {
            this.userRepository.save(member);
            return member;
        } catch (Exception ex) {
            System.out.println("Erro ao criar o usuário: " + ex.getMessage());
            throw ex;
        }
    }

    public void deleteUser(User user) {
        try {
            this.userRepository.delete(user);
            System.out.println("Usuário deletado com sucesso");
        } catch (Exception ex) {
            System.out.println("Erro ao deletar o usuário: " + ex.getMessage());
            throw ex;
        }
    }

    public User login(String email, String password) {
        User user = this.userRepository.findUserByEmail(email);

        if (user!= null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
