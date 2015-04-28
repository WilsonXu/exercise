package org.weber.siia.booking.domain.cancellation;

/**
 * Created by wxu on 4/28/2015.
 */
public class CancellationConfirmation {
    private String reservationCode;
    private String confirmationNumber;
    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
