package org.weber.siia.booking.domain.trip;

import org.weber.siia.booking.domain.user.User;

import java.util.List;

/**
 * Created by wxu on 4/28/2015.
 */
public class BookedTrip {
    private final User user;
    private final List<BookedLeg> bookedLegs;

    public BookedTrip(User user, List<BookedLeg> bookedLegs) {
        this.user = user;
        this.bookedLegs = bookedLegs;
    }

    public List<BookedLeg> getBookedLegs() {
        return bookedLegs;
    }
}
