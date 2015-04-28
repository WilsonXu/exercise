package org.weber.siia.booking.domain.car;

import org.weber.siia.booking.domain.FinancialAmount;

/**
 * Created by wxu on 4/28/2015.
 */
public class CarRentalQuote {
    private final CarRental carRental;
    private final FinancialAmount quote;

    public CarRentalQuote(CarRental carRental, FinancialAmount quote) {
        this.carRental = carRental;
        this.quote = quote;
    }

    public CarRental getCarRental() {
        return carRental;
    }

    public FinancialAmount getQuote() {
        return quote;
    }
}
