package org.cefet.services;

import org.cefet.models.Administrator;
import org.cefet.models.Group;
import org.cefet.models.Investment;
import org.cefet.models.User;
import org.cefet.repositories.InvestmentRepository;

public class InvestmentService {
    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public void addInvestment(Investment investment, User user) {
        user.getInvestments().add(investment);
        System.out.println("Investimento inserido com sucesso.");
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
