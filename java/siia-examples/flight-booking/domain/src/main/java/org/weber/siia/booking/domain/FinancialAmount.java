package org.weber.siia.booking.domain;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by wxu on 4/28/2015.
 */
public class FinancialAmount {
    private final Currency currency;

    private final BigDecimal amount;

    public FinancialAmount(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FinancialAmount add(FinancialAmount financialAmount) {
        checkCurrenciesMatch(financialAmount);
        return new FinancialAmount(getCurrency(), financialAmount.getAmount());
    }

    protected void checkCurrenciesMatch(FinancialAmount financialAmount) {
        if (!financialAmount.getCurrency().equals(getCurrency())) {
            throw new IllegalArgumentException("Can not add financial amounts with different currencies " +
                    getCurrency() + " to " + financialAmount.getCurrency());
        }

    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + getAmount().setScale(2);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof FinancialAmount)){
            return false;
        }
        return super.equals(obj);
    }
}
