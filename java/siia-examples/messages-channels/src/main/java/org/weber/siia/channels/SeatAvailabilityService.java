package org.weber.siia.channels;

/**
 * Created by wilson on 15/4/23.
 */
public class SeatAvailabilityService {
    public SeatConfirmation confirmSeat(ChargedBooking chargedBooking) {
        Seat seat = new Seat("1A");
        return new SeatConfirmation(chargedBooking, seat);
    }
}
