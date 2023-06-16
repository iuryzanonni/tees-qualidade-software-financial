package org.cefet.services;

import org.cefet.enums.FixedIncomeType;
import org.cefet.enums.UserRole;
import org.cefet.models.*;
import org.cefet.repositories.InvestmentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InvestmentServiceTest {
    private Set<Investment> database;
    private InvestmentRepository investmentRepository;
    private InvestmentService investmentService;

    @Before
    public void setUp() {
        this.database = new HashSet<>();
        this.investmentRepository = new InvestmentRepository(this.database);
        this.investmentService = new InvestmentService(this.investmentRepository);
    }

    @Test
    public void shouldAddInvestment() {
        User user = createMember();
        Stock stock = createStock();

        this.investmentService.addInvestment(stock, user);

        assertTrue(user.getInvestments().contains(stock));
    }

    @Test
    public void shouldRemoveInvestment() {
        User user = createMember();
        Stock stock = createStock();
        user.getInvestments().add(stock);

        this.investmentService.removeInvestment(stock, user);

        assertEquals(0, user.getInvestments().size());
    }

    @Test
    public void shouldAddInvestmentToAllMembers() {
        User user_1 = createMember();
        User user_2 = createMember();
        User user_3 = createMember();

        Group group = createGroup();
        group.setUsers(Arrays.asList(user_1, user_2, user_3));

        FixedIncome fixedIncome = createFixedIncome();

        this.investmentService.addInvestmentToGroup(fixedIncome, group);

        group.getUsers().forEach(user -> {
            assertTrue(user.getInvestments().contains(fixedIncome));
        });

    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Alvo");
        member.setEmail("member@teste");
        member.setLastName("Dumbledore");
        member.setPassword("123456");
        member.setUserRole(UserRole.BASIC);

        return member;
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

    private Group createGroup() {
        Group group = new Group();
        group.setName("Grupo Teste");
        group.setDate(new Date());

        return group;
    }
}
