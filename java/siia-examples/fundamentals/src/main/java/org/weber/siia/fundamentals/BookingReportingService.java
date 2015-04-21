package org.weber.siia.fundamentals;

/**
 * Created by wxu on 4/21/2015.
 */
public class BookingReportingService {
    private final BookingDao bookingDao;

    public BookingReportingService() {
        this.bookingDao = new SimpleBookingDao();
    }
}
