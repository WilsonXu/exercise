package org.weber.siia.booking.domain.payment;

import org.weber.siia.booking.domain.FinancialAmount;

/**
 * Created by wxu on 4/28/2015.
 */
public abstract class PaymentSettlement {
    private FinancialAmount amount;

    public FinancialAmount getAmount() {
        return amount;
    }

    public void setAmount(FinancialAmount amount) {
        this.amount = amount;
    }
}
