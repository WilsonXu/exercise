package org.weber.siia.booking.domain.cancellation;

/**
 * Created by wxu on 4/28/2015.
 */
public class CancellationRequest {
    private String reservationCode;
    private final Requestor requestor = new Requestor();

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public Requestor getRequestor() {
        return this.requestor;
    }

    public class Requestor {
        public String getEmailAddress() {
            return "test@example.com";
        }
    }
}
