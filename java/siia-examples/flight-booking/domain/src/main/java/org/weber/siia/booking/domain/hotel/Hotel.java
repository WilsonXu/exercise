package org.weber.siia.booking.domain.hotel;

import org.weber.siia.booking.domain.Location;

/**
 * Created by wxu on 4/28/2015.
 */
public class Hotel {
    private long hotelId;
    private String hotelDescription;
    private String hotelName;
    private Location location;

    public Hotel(long hotelId, String hotelDescription, String hotelName, Location location) {
        this.hotelId = hotelId;
        this.hotelDescription = hotelDescription;
        this.hotelName = hotelName;
        this.location = location;
    }
}
