package org.weber.siia.booking.domain.car;

import org.weber.siia.booking.domain.FinancialAmount;

/**
 * Created by wxu on 4/28/2015.
 */
public class CarRentalBooking {
    private final String confirmationId;
    private final CarRental rental;
    private final FinancialAmount rentalCost;
    private final boolean paid;

    public CarRentalBooking(String confirmationId, CarRental rental, FinancialAmount rentalCost, boolean paid) {
        this.confirmationId = confirmationId;
        this.rental = rental;
        this.rentalCost = rentalCost;
        this.paid = paid;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public CarRental getRental() {
        return rental;
    }

    public FinancialAmount getRentalCost() {
        return rentalCost;
    }

    public boolean isPaid() {
        return paid;
    }
}
