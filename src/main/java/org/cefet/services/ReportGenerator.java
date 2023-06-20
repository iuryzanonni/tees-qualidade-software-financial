package org.cefet.services;

import org.cefet.models.*;
import org.cefet.repositories.GroupRepository;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    private final GroupRepository groupRepository;

    public ReportGenerator(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void generateReport(int groupId) {
        Group group = this.groupRepository.findById(groupId);
        System.out.printf("Grupo: %s\n", group.getName());
        System.out.printf("Total de membros: %d\n\n", group.getUsers().size());

        group.getUsers().forEach(user -> {
            printUser(user);
        });
    }

    private void printUser(User user) {
        System.out.printf("Usuário: %s %s\n", user.getName(), user.getLastName());
        System.out.printf("E-mail: %s\n", user.getEmail());

        System.out.println("Investimentos");
        printInvestment(user.getInvestments());

        System.out.println("Faturas");
        printInvoices(user.getInvoices());

        System.out.println();
    }

    private void printInvestment(List<Investment> investments) {
        List<Stock> stocks = new ArrayList<>();
        List<FixedIncome> incomes = new ArrayList<>();

        investments.forEach(investment -> {
            if (investment instanceof Stock) {
                stocks.add((Stock) investment);
            } else {
                incomes.add((FixedIncome) investment);
            }
        });

        System.out.println("\tAções");
        stocks.forEach(stock -> {
            System.out.printf("\t\t* %s: $ %.2f\n", stock.getCompany(), stock.getAmount() * stock.getValueBuy());
        });

        System.out.println("\tRenda fixa");
        incomes.forEach( fixedIncome -> {
            System.out.printf("\t\t* %s: $ %.2f\n", fixedIncome.getType(), fixedIncome.getValueBuy());
        });
    }

    private void printInvoices(List<Invoice> invoices) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        invoices.forEach(invoice -> {
            System.out.printf("\t* Nome %s\n", invoice.getName());
            System.out.printf("\t\t* Data de vencimento: %s\n", invoice.getDueDate().format(formatter));
            if (invoice.getPaymentDate() != null) {
                System.out.printf("\t\t* Data de pagamento: %s\n", invoice.getPaymentDate().format(formatter));
            } else {
                System.out.printf("\t\t* Data de pagamento: -\n");
            }
        });
    }
}
