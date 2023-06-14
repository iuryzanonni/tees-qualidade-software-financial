package org.cefet.repositories;

import org.cefet.models.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InvoiceRepositoryTest {
    private Map<Integer, Invoice> invoiceDatabase;
    private InvoiceRepository repository;

    @Before
    public void setUp() {
        this.invoiceDatabase = new HashMap<>();
        this.repository = new InvoiceRepository(this.invoiceDatabase);
    }

    @Test
    public void shouldSaveGroup() {
        repository.save(createInvoice());

        assertEquals(1, invoiceDatabase.size());
    }

    @Test
    public void shouldDeleteGroup() {
        Invoice invoice = repository.save(createInvoice());
        repository.delete(invoice);

        assertEquals(0, invoiceDatabase.size());
    }

    @Test
    public void shouldFindGroupById() {
        Invoice invoice = repository.save(createInvoice());
        Invoice result = repository.findById(invoice.getId());

        assertEquals(invoice, result);
    }

    @Test
    public void shouldReturnNullWhenThereIsNotId() {
        repository.save(createInvoice());
        Invoice result = repository.findById(999);

        assertNull(result);
    }

    private Invoice createInvoice() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Invoice invoice = new Invoice();

        try {
            invoice.setCreateDate(formatter.parse("2023-06-13"));
            invoice.setName("Fatura Teste");
            invoice.setDueDate(formatter.parse("2023-06-30"));
        } catch (ParseException e) {
            System.out.println("Formato de data invalido.");
        }

        return invoice;
    }
}
