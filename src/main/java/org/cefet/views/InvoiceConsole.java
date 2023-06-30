package org.cefet.views;

import org.cefet.models.Invoice;
import org.cefet.models.User;
import org.cefet.services.InvoiceService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InvoiceConsole {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService;

    public InvoiceConsole(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void createInvoice(User user) {
        System.out.println("##### Adicionar fatura #####");
        System.out.print("Nome da fatura: ");
        String name = scanner.nextLine();

        System.out.print("Vencimento da fatura (yyyy-mm-dd): ");
        String[] dueDateString = scanner.nextLine().split("-");
        LocalDate dueDate = LocalDate.of(Integer.parseInt(dueDateString[0]), Integer.parseInt(dueDateString[1]),
                Integer.parseInt(dueDateString[2]));

        System.out.print("Valor da fatura: ");
        String value = scanner.nextLine();

        this.invoiceService.createInvoice(name, dueDate, null, Double.valueOf(value), user);

        System.out.println("Fatura criada com sucesso.");
    }

    public void showInvoices(User user) {
        System.out.println("##### Lista de faturas #####");
        List<Invoice> invoices = this.invoiceService.getInvoicesByEmail(user.getEmail());
        invoices.forEach(this::printInvoice);
    }

    private void printInvoice(Invoice invoice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.printf("* Nome: %s #%d\n", invoice.getName(), invoice.getId());
        System.out.printf("* Valor: %.2f\n", invoice.getValue());
        System.out.printf("\t* Data de vencimento: %s\n", invoice.getDueDate().format(formatter));
        if (invoice.getPaymentDate() != null) {
            System.out.printf("\t* Data de pagamento: %s\n", invoice.getPaymentDate().format(formatter));
        } else {
            System.out.println("\t* Data de pagamento: -");
        }
    }
}
