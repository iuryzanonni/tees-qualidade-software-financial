package org.cefet.views;

import org.cefet.enums.UserRole;
import org.cefet.models.User;
import org.cefet.repositories.GroupRepository;
import org.cefet.repositories.InvestmentRepository;
import org.cefet.repositories.InvoiceRepository;
import org.cefet.repositories.UserRepository;
import org.cefet.services.GroupService;
import org.cefet.services.InvestmentService;
import org.cefet.services.InvoiceService;

import java.util.Scanner;

public class UserConsole {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService;
    private final InvestmentService investmentService;
    private final GroupService groupService;
    private final InvoiceConsole invoiceConsole;
    private final InvestmentConsole investmentConsole;

    public UserConsole(InvoiceRepository invoiceRepository, InvestmentRepository investmentRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.investmentService = new InvestmentService(investmentRepository, userRepository);
        this.groupService = new GroupService(groupRepository);
        this.invoiceService = new InvoiceService(invoiceRepository, userRepository);
        this.invoiceConsole = new InvoiceConsole(invoiceService);
        this.investmentConsole = new InvestmentConsole(investmentService);
    }

    public int run(User user) {
        int option = 999;
        while (option != 9) {
            System.out.println("##### Area do usuário #####");
            System.out.println("1 - Listar faturas");
            System.out.println("2 - Listar investimento");
            System.out.println("3 - Cadastrar faturas");
            System.out.println("4 - Cadastrar investimento");

            if (user.getUserRole() == UserRole.ADMINISTRATOR) {
                System.out.println("5 - Inserir fatura para grupo");
                System.out.println("6 - Inserir investimento para grupo");
            }

            System.out.println("9 - Logout");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    invoiceConsole.showInvoices(user);
                    break;
                case 2:
                    investmentConsole.showInvestments(user);
                    break;
                case 3:
                    invoiceConsole.createInvoice(user);
                    break;
                case 4:
                    investmentConsole.createInvestment(user);
                    break;
            }
        }
        return 0;
    }
}
