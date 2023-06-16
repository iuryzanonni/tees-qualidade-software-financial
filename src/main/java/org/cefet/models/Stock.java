package org.cefet.models;

import java.time.LocalDate;

public class Stock extends Investment{
    private String company;
    private String ticker;

    public Stock(LocalDate dateInvestment, double valueBuy, double valueSale, String company, String ticker) {
        super(dateInvestment, valueBuy, valueSale);
        this.company = company;
        this.ticker = ticker;
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
}
