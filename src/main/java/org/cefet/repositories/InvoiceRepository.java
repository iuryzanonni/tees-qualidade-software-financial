package org.cefet.repositories;

import org.cefet.models.Invoice;
import org.cefet.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InvoiceRepository extends Repository<Invoice> {
    public InvoiceRepository(Set<Invoice> database) {
        super(database);
    }

    public List<Invoice> findInvoicesByUser(User user) {
        return this.database.stream()
                .filter(invoice -> invoice.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
