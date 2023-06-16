package org.cefet.models;

import java.time.LocalDate;
import java.util.Date;

public abstract class Investment extends EntityBase {
    private LocalDate dateInvestment;
    private Double valueBuy;
    private Double valueSale;

    public Investment(LocalDate dateInvestment, Double valueBuy, Double valueSale) {
        this.dateInvestment = dateInvestment;
        this.valueBuy = valueBuy;
        this.valueSale = valueSale;
    }

    public Investment() {}

    public LocalDate getDateInvestment() {
        return dateInvestment;
    }

    public void setDateInvestment(LocalDate dateInvestment) {
        this.dateInvestment = dateInvestment;
    }

    public Double getValueBuy() {
        return valueBuy;
    }

    public void setValueBuy(Double valueBuy) {
        this.valueBuy = valueBuy;
    }

    public Double getValueSale() {
        return valueSale;
    }

    public void setValueSale(Double valueSale) {
        this.valueSale = valueSale;
    }
}
