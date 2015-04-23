package org.weber.siia.channels;

/**
 * Created by wilson on 15/4/23.
 */
public class BillForBookingService {
    public ChargedBooking pay(Booking booking) {
        return new ChargedBooking(booking, 1l);
    }
}
