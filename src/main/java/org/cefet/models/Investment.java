package org.cefet.models;

import java.util.Date;

public abstract class Investment extends EntityBase {
    private Date dateInvestment;
    private double valueBuy;
    private double valueSale;

    public Investment(Date dateInvestment, double valueBuy, double valueSale) {
        this.dateInvestment = dateInvestment;
        this.valueBuy = valueBuy;
        this.valueSale = valueSale;
    }

    public Investment() {}

    public Date getDateInvestment() {
        return dateInvestment;
    }

    public void setDateInvestment(Date dateInvestment) {
        this.dateInvestment = dateInvestment;
    }

    public double getValueBuy() {
        return valueBuy;
    }

    public void setValueBuy(double valueBuy) {
        this.valueBuy = valueBuy;
    }

    public double getValueSale() {
        return valueSale;
    }

    public void setValueSale(double valueSale) {
        this.valueSale = valueSale;
    }
}
