package org.weber.siia.fundamentals;

/**
 * Created by wilson on 15/4/21.
 */
public class Booking {
    private Long flightRef;

    public Booking(Long flightRef) {
        this.flightRef = flightRef;
    }

    public Long getFlightRef() {
        return flightRef;
    }
}
