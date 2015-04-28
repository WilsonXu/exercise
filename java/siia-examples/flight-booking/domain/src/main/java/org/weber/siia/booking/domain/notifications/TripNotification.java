package org.weber.siia.booking.domain.notifications;

import org.weber.siia.booking.domain.trip.Trip;

/**
 * Created by wxu on 4/28/2015.
 */
public class TripNotification {
    private final Trip trip;
    private final String message;

    public TripNotification(Trip trip, String message) {
        this.trip = trip;
        this.message = message;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getMessage() {
        return message;
    }
}
