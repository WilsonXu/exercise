package org.weber.siia.booking.domain.car;

import org.weber.siia.booking.domain.Location;

import java.util.Date;

/**
 * Created by wxu on 4/28/2015.
 */
public class CarRental {
    private Location collectionLocation;
    private Location dropOffLocation;
    private Date collectionDate;
    private Date dropOffDate;
    private String bookingConfirmation;

    public CarRental(Location collectionLocation, Location dropOffLocation, Date collectionDate, Date dropOffDate, String bookingConfirmation) {
        this.collectionLocation = collectionLocation;
        this.dropOffLocation = dropOffLocation;
        this.collectionDate = collectionDate;
        this.dropOffDate = dropOffDate;
        this.bookingConfirmation = bookingConfirmation;
    }

    public Location getCollectionLocation() {
        return collectionLocation;
    }

    public void setCollectionLocation(Location collectionLocation) {
        this.collectionLocation = collectionLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getBookingConfirmation() {
        return bookingConfirmation;
    }

    public void setBookingConfirmation(String bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }
}
