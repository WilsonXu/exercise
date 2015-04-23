package org.weber.siia.channels;

/**
 * Created by wilson on 15/4/23.
 */
public class ChargedBooking {
    private Booking booking;
    private Long confirmationNumber;

    public ChargedBooking(Booking booking, Long confirmationNumber) {
        this.booking = booking;
        this.confirmationNumber = confirmationNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public Long getConfirmationNumber() {
        return confirmationNumber;
    }
}
