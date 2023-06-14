package org.cefet.repositories;

import org.cefet.models.Investment;

import java.util.Map;

public class InvestmentRepository extends Repository<Investment> {
    public InvestmentRepository(Map<Integer, Investment> database) {
        super(database);
    }
}
