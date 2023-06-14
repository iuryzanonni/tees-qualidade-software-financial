package org.cefet.repositories;

import org.cefet.enums.FixedIncomeType;
import org.cefet.models.FixedIncome;
import org.cefet.models.Investment;
import org.cefet.models.Stock;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InvestmentRepositoryTest {
    private Map<Integer, Investment> investmentDatabase;
    private InvestmentRepository repository;

    @Before
    public void setUp() {
        this.repository = new InvestmentRepository();
        this.investmentDatabase = new HashMap<>();
    }

    @Test
    public void shouldSaveMultipleInvestments() {
        Stock stock = createStock();
        FixedIncome fixedIncome = createFixedIncome();

        repository.save(stock, investmentDatabase);
        repository.save(fixedIncome, investmentDatabase);

        assertEquals(2, investmentDatabase.size());
    }

    @Test
    public void shouldDeleteInvestment() {
        Stock stock = createStock();
        FixedIncome fixedIncome = createFixedIncome();

        repository.save(stock, investmentDatabase);
        repository.save(fixedIncome, investmentDatabase);

        repository.delete(stock, investmentDatabase);

        assertEquals(1, investmentDatabase.size());
    }

    @Test
    public void shouldFindInvestmentById() {
        Stock stock = createStock();
        FixedIncome fixedIncome = createFixedIncome();

        repository.save(stock, investmentDatabase);
        repository.save(fixedIncome, investmentDatabase);

        Investment investment = repository.findById(2, investmentDatabase);

        assertEquals(fixedIncome, investment);
    }

    private Stock createStock() {
        Stock stock = new Stock();
        stock.setCompany("Apple");
        stock.setTicker("AAPL");
        stock.setValueBuy(24.96);

        return stock;
    }

    private FixedIncome createFixedIncome() {
        FixedIncome fixedIncome = new FixedIncome();
        fixedIncome.setType(FixedIncomeType.LCI);
        fixedIncome.setValueBuy(999);

        return fixedIncome;
    }
}
