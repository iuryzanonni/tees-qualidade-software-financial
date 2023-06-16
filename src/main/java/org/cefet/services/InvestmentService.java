package org.cefet.services;

import org.cefet.models.*;
import org.cefet.repositories.InvestmentRepository;

import java.time.LocalDate;
import java.util.Date;

public class InvestmentService {
    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public void addInvestmentToUser(Investment investment, User user) {
        user.getInvestments().add(investment);
        System.out.println("Investimento inserido com sucesso.");
    }

    public Investment createStock(String company, String ticker, LocalDate dateInvestment, double valueBuy, Double valueSale) {
        Stock stock = new Stock();
        stock.setCompany(company);
        stock.setTicker(ticker);
        stock.setDateInvestment(dateInvestment);
        stock.setValueBuy(valueBuy);
        stock.setValueSale(valueSale);

        return investmentRepository.save(stock);
    }

    public void removeInvestment(Investment investment, User user) {
        user.getInvestments().remove(investment);
        System.out.println("Investimento removido com sucesso.");
    }

    public void addInvestmentToGroup(Investment investment, Group group) {
        group.getUsers().forEach(user -> {
            user.getInvestments().add(investment);
            System.out.printf("Investimento adicionado para %s\n", user.getName());
        });
    }
}
