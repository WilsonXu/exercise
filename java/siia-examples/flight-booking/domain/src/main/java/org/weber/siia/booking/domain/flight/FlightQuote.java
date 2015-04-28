package org.weber.siia.booking.domain.flight;

import org.joda.time.DateTime;

/**
 * Created by wxu on 4/28/2015.
 */
public class FlightQuote {
    private final DateTime bookingDateTime;
    private final Flight flight;
    public FlightQuote(DateTime bookingDateTime, Flight flight) {
        this.bookingDateTime = bookingDateTime;
        this.flight = flight;
    }

    public DateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public Flight getFlight() {
        return flight;
    }
}
