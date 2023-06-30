package org.cefet;

import org.cefet.enums.UserRole;
import org.cefet.models.*;
import org.cefet.views.LoginConsole;
import org.cefet.repositories.GroupRepository;
import org.cefet.repositories.InvestmentRepository;
import org.cefet.repositories.InvoiceRepository;
import org.cefet.repositories.UserRepository;
import org.cefet.views.UserConsole;

import java.util.HashSet;
import java.util.Set;

public class ApplicationConsoleRunner {
    private Set<Group> groupDatabase;
    private Set<Investment> investmentDatabase;
    private Set<Invoice> invoiceDatabase;
    private Set<User> userDatabase;
    private final GroupRepository groupRepository;
    private final InvestmentRepository investmentRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final LoginConsole loginConsole;
    private final UserConsole userConsole;

    public ApplicationConsoleRunner() {
        initDatabases();
        this.groupRepository = new GroupRepository(groupDatabase);
        this.investmentRepository = new InvestmentRepository(investmentDatabase);
        this.invoiceRepository = new InvoiceRepository(invoiceDatabase);
        this.userRepository = new UserRepository(userDatabase);
        this.loginConsole = new LoginConsole(userRepository);
        this.userConsole = new UserConsole(invoiceRepository, investmentRepository, groupRepository, userRepository);
    }

    public void run() {
        startData();
        int option = 1;
        while (option != 0) {
            System.out.println("##### Seja bem-vindo ao sistema financeiro #####");
            User tokenUser = this.loginConsole.run();
            if (tokenUser != null) {
                this.userConsole.run(tokenUser);
            } else {
                option = 0;
            }
        }
    }

    private void initDatabases() {
        this.groupDatabase = new HashSet<>();
        this.investmentDatabase = new HashSet<>();
        this.invoiceDatabase = new HashSet<>();
        this.userDatabase = new HashSet<>();
    }

    private void startData() {
        Administrator administrator = new Administrator();
        administrator.setName("Iury");
        administrator.setLastName("Faria");
        administrator.setEmail("test@test");
        administrator.setPassword("test123");
        administrator.setUserRole(UserRole.ADMINISTRATOR);

        this.userRepository.save(administrator);
    }
}
