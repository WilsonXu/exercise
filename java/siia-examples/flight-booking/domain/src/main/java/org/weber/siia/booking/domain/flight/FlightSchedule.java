package org.weber.siia.booking.domain.flight;

import org.joda.time.DateTime;
import org.joda.time.Partial;

/**
 * Created by wxu on 4/28/2015.
 */
public class FlightSchedule {
    private final DateTime validFromInclusive;
    private final DateTime validUntilInclusive;
    private final Partial flightTimeOfDay;

    public FlightSchedule(DateTime validFromInclusive, DateTime validUntilInclusive, Partial flightTimeOfDay) {
        this.validFromInclusive = validFromInclusive;
        this.validUntilInclusive = validUntilInclusive;
        this.flightTimeOfDay = flightTimeOfDay;
    }

    public DateTime getValidFromInclusive() {
        return validFromInclusive;
    }

    public DateTime getValidUntilInclusive() {
        return validUntilInclusive;
    }

    public Partial getFlightTimeOfDay() {
        return flightTimeOfDay;
    }
}
