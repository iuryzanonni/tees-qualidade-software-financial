package org.cefet.services;

import org.cefet.models.Group;
import org.cefet.models.Invoice;
import org.cefet.models.User;
import org.cefet.repositories.InvoiceRepository;
import org.cefet.repositories.UserRepository;
import org.cefet.services.payment.IPaymentService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    public Invoice createInvoice(String name, LocalDate dueDate, LocalDate paymentDate, Double value, User user) {
        Invoice invoice = new Invoice(name, dueDate, paymentDate, value, user);

        return invoiceRepository.save(invoice);
    }

    public void payInvoice(int invoiceId, String userEmail, IPaymentService paymentService) {

        Invoice invoice = this.invoiceRepository.findById(invoiceId);
        User user = this.userRepository.findUserByEmail(userEmail);

        UUID transactionId = paymentService.pay(user.getId(), invoiceId, invoice.getValue());

        if (transactionId == null) {
            System.out.println("Pagamento não autorizado.");
            return;
        }

        invoice.setPaymentDate(LocalDate.now());
    }

    public void addInvoiceToGroup(Invoice invoice, Group group) {
        group.getUsers().forEach(user -> {
            user.getInvoices().add(invoice);
        });
    }

    public List<Invoice> getInvoicesByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);

        return this.invoiceRepository.findInvoicesByUser(user);
    }
}
