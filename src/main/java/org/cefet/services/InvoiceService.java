package org.cefet.services;

import org.cefet.models.Invoice;
import org.cefet.models.User;
import org.cefet.repositories.InvoiceRepository;
import org.cefet.repositories.UserRepository;
import org.cefet.services.payment.IPaymentService;

import java.time.LocalDate;
import java.util.UUID;

public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final IPaymentService paymentService;

    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository,
                          IPaymentService paymentService) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
    }

    public Invoice createInvoice(String name, LocalDate dueDate, LocalDate paymentDate, Double value) {
        Invoice invoice = new Invoice(name, dueDate, paymentDate, value);

        return invoiceRepository.save(invoice);
    }

    public void payInvoice(int invoiceId, String userEmail) {

        Invoice invoice = this.invoiceRepository.findById(invoiceId);
        User user = this.userRepository.findUserByEmail(userEmail);

        UUID transactionId = this.paymentService.pay(user.getId(), invoiceId, invoice.getValue());

        if (transactionId == null) {
            System.out.println("Pagamento não autorizado.");
            return;
        }

        invoice.setPaymentDate(LocalDate.now());
    }
}
