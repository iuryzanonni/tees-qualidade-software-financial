package org.cefet.models;

import java.time.LocalDate;

public class Stock extends Investment {
    private String company;
    private String ticker;

    private int amount;

    public Stock(LocalDate dateInvestment, Double valueBuy, Double valueSale, User user, String company, String ticker, int amount) {
        super(dateInvestment, valueBuy, valueSale, user);
        this.company = company;
        this.ticker = ticker;
        this.amount = amount;
    }

    public Stock() {}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
