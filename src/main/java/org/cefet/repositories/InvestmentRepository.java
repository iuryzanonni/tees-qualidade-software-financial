package org.cefet.repositories;

import org.cefet.models.Investment;

import java.util.Set;

public class InvestmentRepository extends Repository<Investment> {
    public InvestmentRepository(Set<Investment> database) {
        super(database);
    }
}
