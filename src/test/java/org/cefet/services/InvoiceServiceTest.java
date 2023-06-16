package org.cefet.services;

import org.cefet.enums.UserRole;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        this.invoiceService = new InvoiceService(this.invoiceRepository, this.userRepository, this.paymentService);
    }

    @Test
    public void shouldCreateInvoice() {
        Invoice invoice = this.invoiceService.createInvoice(NAME, DUE_DATE, null, VALUE);

        assertTrue(this.invoicesDatabase.contains(invoice));
    }

    @Test
    public void shouldPayInvoice() {
        Invoice invoice = this.invoiceService.createInvoice(NAME, DUE_DATE, null, VALUE);
        Member member = createMember();

        this.invoiceService.payInvoice(invoice.getId(), member.getEmail());

        assertNotNull(invoice.getPaymentDate());
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
}
