package org.cefet.services;

import org.cefet.enums.FixedIncomeType;
import org.cefet.models.*;
import org.cefet.repositories.InvestmentRepository;
import org.cefet.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    public InvestmentService(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    public void addInvestmentToUser(Investment investment, User user) {
        user.getInvestments().add(investment);
        System.out.println("Investimento inserido com sucesso.");
    }

    public Investment createStock(String company, String ticker, LocalDate dateInvestment, double valueBuy,
                                  Double valueSale, int amount, User user) {
        Stock stock = new Stock();
        stock.setCompany(company);
        stock.setTicker(ticker);
        stock.setAmount(amount);
        stock.setDateInvestment(dateInvestment);
        stock.setValueBuy(valueBuy);
        stock.setValueSale(valueSale);
        stock.setUser(user);

        return investmentRepository.save(stock);
    }

    public Investment createFixedIncome(FixedIncomeType type, Double valueBuy, Double valueSale,
                                        LocalDate dateInvestment, User user) {
        FixedIncome fixedIncome = new FixedIncome();
        fixedIncome.setType(type);
        fixedIncome.setValueBuy(valueBuy);
        fixedIncome.setValueSale(valueSale);
        fixedIncome.setDateInvestment(dateInvestment);
        fixedIncome.setUser(user);

        return investmentRepository.save(fixedIncome);
    }

    public List<Investment> getInvestmentsByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);
        return this.investmentRepository.findInvestmentsByUser(user);
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
