package org.cefet.repositories;

import org.cefet.models.Invoice;

import java.util.Map;
import java.util.Set;

public class InvoiceRepository extends Repository<Invoice> {
    public InvoiceRepository(Set<Invoice> database) {
        super(database);
    }
}
