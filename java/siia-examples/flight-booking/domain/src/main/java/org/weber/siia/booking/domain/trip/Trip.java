package org.weber.siia.booking.domain.trip;

import java.util.Collections;
import java.util.List;

/**
 * Created by wxu on 4/28/2015.
 */
public class Trip {
    private List<Leg> legs;

    public Trip(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(this.legs);
    }
}
