package org.weber.siia.fundamentals;

/**
 * Created by wilson on 15/4/21.
 */
public class SimpleBookingDao implements BookingDao {
    @Override
    public Booking getBookById(Long bookingReference) {
        return new Booking(1l);
    }
}
