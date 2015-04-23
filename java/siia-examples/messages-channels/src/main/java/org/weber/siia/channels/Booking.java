package org.weber.siia.channels;

/**
 * Created by wilson on 15/4/23.
 */
public class Booking {
    private String flightId;
    private String customerEmail;

    public Booking() {
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
