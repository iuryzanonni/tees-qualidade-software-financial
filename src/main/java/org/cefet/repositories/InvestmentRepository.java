package org.cefet.repositories;

import org.cefet.models.Investment;
import org.cefet.models.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentRepository extends Repository<Investment> {
    public InvestmentRepository(Set<Investment> database) {
        super(database);
    }

    public List<Investment> findInvestmentsByUser(User user) {
        return this.database.stream()
                .filter(investment -> investment.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
