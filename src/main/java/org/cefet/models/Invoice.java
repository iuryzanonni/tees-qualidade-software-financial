package org.cefet.models;

import java.time.LocalDate;
import java.util.Date;

public class Invoice extends EntityBase {
    private String name;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    private Double value;

    public Invoice(String name, LocalDate dueDate, LocalDate paymentDate, Double value) {
        this.name = name;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.value = value;
    }

    public Invoice() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
