package org.cefet.repositories;

import org.cefet.models.Invoice;

import java.util.Map;

public class InvoiceRepository extends Repository<Invoice> {
    public InvoiceRepository(Map<Integer, Invoice> database) {
        super(database);
    }
}
