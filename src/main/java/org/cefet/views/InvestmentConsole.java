package org.cefet.views;

import org.cefet.enums.FixedIncomeType;
import org.cefet.models.FixedIncome;
import org.cefet.models.Investment;
import org.cefet.models.Stock;
import org.cefet.models.User;
import org.cefet.services.InvestmentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvestmentConsole {
    private final Scanner scanner = new Scanner(System.in);

    private final InvestmentService investmentService;

    public InvestmentConsole(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    public void createInvestment(User user) {
        System.out.println("##### Adicionar Investimento #####");
        System.out.println("Qual o tipo de investimento?");
        System.out.println("1 - Acoes");
        System.out.println("2 - Renda Fixa");

        int option = scanner.nextInt();

        if (option == 1) {
            createStock(user);
        } else if (option == 2) {
            createFixedIncome(user);
        }
    }

    private void createStock(User user) {
        System.out.print("Empresa: ");
        scanner.nextLine();
        String company = scanner.nextLine();

        System.out.print("Ticker: ");
        String ticker = scanner.nextLine();

        System.out.print("Quantidade: ");
        int amount = scanner.nextInt();

        System.out.print("Valor: ");
        double value = scanner.nextDouble();

        this.investmentService.createStock(company, ticker, LocalDate.now(), value, null, amount, user);
    }

    private void createFixedIncome(User user) {
        System.out.print("Tipo: ");
        scanner.nextLine();
        String type = scanner.nextLine();

        System.out.print("Valor: ");
        double value = scanner.nextDouble();

        this.investmentService.createFixedIncome(FixedIncomeType.valueOf(type), value, null, LocalDate.now(), user);
    }

    public void showInvestments(User user) {
        System.out.println("##### Lista de investimentos #####");
        List<Investment> investments = this.investmentService.getInvestmentsByEmail(user.getEmail());

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
}
