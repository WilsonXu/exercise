package org.weber.siia.booking.domain.hotel;

import org.weber.siia.booking.domain.FinancialAmount;

import java.util.Date;

/**
 * Created by wxu on 4/28/2015.
 */
public class HotelQuote {
    private final Hotel hotel;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final FinancialAmount financialAmount;

    public HotelQuote(Hotel hotel, Date checkInDate, Date checkOutDate, FinancialAmount financialAmount) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.financialAmount = financialAmount;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public FinancialAmount getFinancialAmount() {
        return financialAmount;
    }
}
