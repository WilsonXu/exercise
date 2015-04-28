package org.weber.siia.booking.domain.flight;

/**
 * Created by wxu on 4/28/2015.
 */
public class FlightCriteria {
    private FlightSeatClass requiredSeatClass = FlightSeatClass.Economy;
    private boolean returnRequired = true;

    public FlightSeatClass getRequiredSeatClass() {
        return requiredSeatClass;
    }

    public void setRequiredSeatClass(FlightSeatClass requiredSeatClass) {
        this.requiredSeatClass = requiredSeatClass;
    }

    public boolean isReturnRequired() {
        return returnRequired;
    }

    public void setReturnRequired(boolean returnRequired) {
        this.returnRequired = returnRequired;
    }
}
