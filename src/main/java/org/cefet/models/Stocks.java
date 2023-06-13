package org.cefet.models;

import java.util.Date;

public class Stocks extends Investment{
    private String company;

    public Stocks(Date dateInvestment, double valueBuy, double valueSale, String company) {
        super(dateInvestment, valueBuy, valueSale);
        this.company = company;
    }

    public Stocks() {}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
