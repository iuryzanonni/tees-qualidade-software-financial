package org.cefet.services;

import org.cefet.enums.FixedIncomeType;
import org.cefet.enums.UserRole;
import org.cefet.models.*;
import org.cefet.repositories.GroupRepository;
import org.cefet.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ReportGeneratorTest {
    private Set<Group> groupDatabase;
    private Set<User> userDatabase;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private ReportGenerator reportGenerator;

    @Before
    public void setUp() {
        this.groupDatabase = new HashSet<>();
        this.userDatabase = new HashSet<>();
        this.groupRepository = new GroupRepository(this.groupDatabase);
        this.userRepository = new UserRepository(this.userDatabase);
        this.reportGenerator = new ReportGenerator(groupRepository);
    }

    @Test
    public void shouldGenerateReport() {
        Group group = createGroup();
        this.reportGenerator.generateReport(group.getId());
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(name + "@test");
        member.setLastName("Dumbledore");
        member.setPassword("123456");
        member.setUserRole(UserRole.BASIC);

        this.userRepository.save(member);
        return member;
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("Group Test");

        Stock stock1 = createStock("Apple Inc.", "AAPL", 145.67, 20);
        Stock stock2 = createStock("Microsoft Corporation", "MSFT", 259.35, 60);
        Stock stock3 = createStock("Amazon.com Inc.", "AMZN", 3499.12, 200);
        Stock stock4 = createStock("Tesla Inc.", "TSLA", 623.71, 1000);
        Stock stock5 = createStock("Google LLC", "GOOGL", 2510.43, 100);
        Stock stock6 = createStock("Facebook Inc.", "FB", 330.15, 70);
        Stock stock7 = createStock("Netflix Inc.", "NFLX", 517.76, 25);
        Stock stock8 = createStock("Johnson & Johnson", "JNJ", 167.84, 40);
        Stock stock9 = createStock("Procter & Gamble Co.", "PG", 135.28, 15);
        Stock stock10 = createStock("Walmart Inc.", "WMT", 139.52, 55);

        FixedIncome fixedIncome1 = createFixedIncome(FixedIncomeType.CDB, 1000.0);
        FixedIncome fixedIncome2 = createFixedIncome(FixedIncomeType.LCI, 5000.0);
        FixedIncome fixedIncome3 = createFixedIncome(FixedIncomeType.LCA, 2000.0);
        FixedIncome fixedIncome4 = createFixedIncome(FixedIncomeType.CDB, 3000.0);
        FixedIncome fixedIncome5 = createFixedIncome(FixedIncomeType.LCI, 8000.0);
        FixedIncome fixedIncome6 = createFixedIncome(FixedIncomeType.LCA, 1500.0);
        FixedIncome fixedIncome7 = createFixedIncome(FixedIncomeType.CDB, 2500.0);
        FixedIncome fixedIncome8 = createFixedIncome(FixedIncomeType.LCI, 4000.0);
        FixedIncome fixedIncome9 = createFixedIncome(FixedIncomeType.LCA, 7000.0);
        FixedIncome fixedIncome10 = createFixedIncome(FixedIncomeType.CDB, 6000.0);

        Invoice invoice1 = createInvoice("Fatura 1", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 6, 28));
        Invoice invoice2 = createInvoice("Fatura 2", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 6, 29));
        Invoice invoice3 = createInvoice("Fatura 3", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 6, 30));
        Invoice invoice4 = createInvoice("Fatura 4", LocalDate.of(2023, 6, 30), null);
        Invoice invoice5 = createInvoice("Fatura 5", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 1));
        Invoice invoice6 = createInvoice("Fatura 6", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 2));
        Invoice invoice7 = createInvoice("Fatura 7", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 3));
        Invoice invoice8 = createInvoice("Fatura 8", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 4));
        Invoice invoice9 = createInvoice("Fatura 9", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 5));
        Invoice invoice10 = createInvoice("Fatura 10", LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 6));

        Member member = createMember("Alvo");
        member.getInvestments().add(stock1);
        member.getInvestments().add(stock6);
        member.getInvestments().add(fixedIncome1);
        member.getInvestments().add(fixedIncome2);
        member.getInvoices().add(invoice1);
        member.getInvoices().add(invoice2);
        group.getUsers().add(member);

        member = createMember("Sirius");
        member.getInvestments().add(stock2);
        member.getInvestments().add(stock7);
        member.getInvestments().add(fixedIncome3);
        member.getInvestments().add(fixedIncome4);
        member.getInvoices().add(invoice3);
        member.getInvoices().add(invoice4);
        group.getUsers().add(member);

        member = createMember("Minerva");
        member.getInvestments().add(stock3);
        member.getInvestments().add(stock8);
        member.getInvestments().add(fixedIncome5);
        member.getInvestments().add(fixedIncome6);
        member.getInvoices().add(invoice5);
        member.getInvoices().add(invoice6);
        group.getUsers().add(member);

        member = createMember("Rony");
        member.getInvestments().add(stock4);
        member.getInvestments().add(stock9);
        member.getInvestments().add(fixedIncome7);
        member.getInvestments().add(fixedIncome8);
        member.getInvoices().add(invoice7);
        member.getInvoices().add(invoice8);
        group.getUsers().add(member);

        member = createMember("Hermione");
        member.getInvestments().add(stock5);
        member.getInvestments().add(stock10);
        member.getInvestments().add(fixedIncome9);
        member.getInvestments().add(fixedIncome10);
        member.getInvoices().add(invoice9);
        member.getInvoices().add(invoice10);
        group.getUsers().add(member);

        this.groupRepository.save(group);
        return  group;
    }

    private Stock createStock(String company, String ticker, Double value, int amount) {
        Stock stock = new Stock();
        stock.setCompany(company);
        stock.setTicker(ticker);
        stock.setValueBuy(value);
        stock.setAmount(amount);

        return stock;
    }

    private FixedIncome createFixedIncome(FixedIncomeType type, double value) {
        FixedIncome fixedIncome = new FixedIncome();
        fixedIncome.setType(type);
        fixedIncome.setValueBuy(value);

        return fixedIncome;
    }

    private Invoice createInvoice(String name, LocalDate dueDate, LocalDate paymentDate) {
        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setDueDate(LocalDate.of(2023, 06, 30));
        invoice.setPaymentDate(paymentDate);

        return invoice;
    }
}
