package org.cefet.services;

import org.cefet.enums.UserRole;
import org.cefet.models.Group;
import org.cefet.models.Invoice;
import org.cefet.models.Member;
import org.cefet.models.User;
import org.cefet.repositories.InvoiceRepository;
import org.cefet.repositories.UserRepository;
import org.cefet.services.payment.CardPaymentService;
import org.cefet.services.payment.IPaymentService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class InvoiceServiceTest {

    private final String NAME = "Fatura cartão de crédito";
    private final LocalDate DUE_DATE = LocalDate.of(2023,06,10);
    private final Double VALUE = 39.90;

    private Set<Invoice> invoicesDatabase;
    private Set<User> usersDatabase;
    private InvoiceRepository invoiceRepository;
    private UserRepository userRepository;
    private IPaymentService paymentService;
    private InvoiceService invoiceService;

    @Before
    public void setUp() {
        this.invoicesDatabase = new HashSet<>();
        this.usersDatabase = new HashSet<>();
        this.invoiceRepository = new InvoiceRepository(this.invoicesDatabase);
        this.userRepository = new UserRepository(this.usersDatabase);
        this.paymentService = new CardPaymentService();
        this.invoiceService = new InvoiceService(this.invoiceRepository, this.userRepository);
    }

    @Test
    public void shouldCreateInvoice() {
        Invoice invoice = this.invoiceService.createInvoice(NAME, DUE_DATE, null, VALUE, createMember());

        assertTrue(this.invoicesDatabase.contains(invoice));
    }

    @Test
    public void shouldPayInvoice() {
        Member member = createMember();
        Invoice invoice = this.invoiceService.createInvoice(NAME, DUE_DATE, null, VALUE, member);

        this.invoiceService.payInvoice(invoice.getId(), member.getEmail(), paymentService);

        assertNotNull(invoice.getPaymentDate());
    }

    @Test
    public void shouldAddInvoiceToGroup() {
        Invoice invoice = new Invoice();
        Group group = createGroup();

        for(int i = 0; i<=5; i++) {
            group.getUsers().add(createMember());
        }

        this.invoiceService.addInvoiceToGroup(invoice, group);

        for (User user: group.getUsers()) {
            assertTrue(user.getInvoices().contains(invoice));
            assertEquals(1, user.getInvoices().size());
        }
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Alvo");
        member.setEmail("member@teste");
        member.setLastName("Dumbledore");
        member.setPassword("123456");
        member.setUserRole(UserRole.BASIC);

        this.userRepository.save(member);
        return member;
    }

    private Group createGroup() {
        Group group = new Group();
        group.setName("Group Test");

        return  group;
    }
}
