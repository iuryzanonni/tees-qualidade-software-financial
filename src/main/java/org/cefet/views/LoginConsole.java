package org.cefet.views;

import org.cefet.enums.UserRole;
import org.cefet.models.User;
import org.cefet.repositories.UserRepository;
import org.cefet.services.UserService;

import java.util.Scanner;

public class LoginConsole {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;

    public LoginConsole(UserRepository userRepository) {
        this.userService = new UserService(userRepository);
    }

    public User run() {
        int option = 999;
        while (option != 9) {
            System.out.println("##### Login #####");

            System.out.println("1 - Login");
            System.out.println("2 - Realizar cadastro");
            System.out.println("9 - Sair");

            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                return login();
            }

            if (option == 2) {
                return createUser();
            }

            return null;
        }
        return null;
    }

    private User login() {
        System.out.printf("Email: ");
        String email = scanner.nextLine();

        System.out.printf("Password: ");
        String password = scanner.nextLine();

        User user = this.userService.login(email, password);

        if (user == null) {
            System.out.println("E-mail ou senha incorretos.");
            this.run();
        }
        return user;
    }

    private User createUser() {
        System.out.println("##### Cadastro de usuário #####");

        System.out.printf("Primeiro nome: ");
        String firstName = scanner.nextLine();

        System.out.printf("Último nome: ");
        String lastName = scanner.nextLine();

        System.out.printf("Email: ");
        String email = scanner.nextLine();

        System.out.printf("Password: ");
        String password = scanner.nextLine();

        System.out.println("Qual tipo de usuário?");
        System.out.println("1 - Básico");
        System.out.println("2 - Administrador");

        int option = scanner.nextInt();
        scanner.nextLine();
        UserRole userRole = null;
        
        if (option == 1) {
            userRole = UserRole.BASIC;
        }

        if (option == 2) {
            userRole = UserRole.ADMINISTRATOR;
        }
        
        User user = this.userService.createUser(firstName, lastName, email, password, userRole);

        if (user == null) {
            System.out.println("Não foi possível criar o usuário.");
        }

        System.out.println("Usuário criado com sucesso.");

        return user;
    }
}
