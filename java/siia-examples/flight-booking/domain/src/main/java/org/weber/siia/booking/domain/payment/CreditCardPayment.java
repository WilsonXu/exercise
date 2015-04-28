package org.weber.siia.booking.domain.payment;

import java.util.Date;

/**
 * Created by wxu on 4/28/2015.
 */
public class CreditCardPayment extends PaymentSettlement {
    private CreditCardType creditCardType;
    private String accountNumber;
    private Date expiryDate;
    private String cardholderName;

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getCardholderName()
    {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName)
    {
        this.cardholderName = cardholderName;
    }

    public CreditCardType getCreditCardType()
    {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType)
    {
        this.creditCardType = creditCardType;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }
}
