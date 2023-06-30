package org.cefet.models;

import org.cefet.enums.FixedIncomeType;

import java.time.LocalDate;

public class FixedIncome extends Investment{
    private FixedIncomeType type;

    public FixedIncome(LocalDate dateInvestment, Double valueBuy, Double valueSale, User user, FixedIncomeType type) {
        super(dateInvestment, valueBuy, valueSale, user);
        this.type = type;
    }

    public FixedIncome() {}

    public FixedIncomeType getType() {
        return type;
    }

    public void setType(FixedIncomeType type) {
        this.type = type;
    }
}
