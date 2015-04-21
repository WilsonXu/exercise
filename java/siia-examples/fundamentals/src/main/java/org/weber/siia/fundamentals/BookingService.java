package org.weber.siia.fundamentals;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Created by wilson on 15/4/21.
 */
public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public MealPreference populatePreference(MealPreference mealPreference) {
        Booking booking = this.bookingDao.getBookById(mealPreference.getBookingId());
        mealPreference.setFlightReference(booking.getFlightRef());
        return mealPreference;
    }
}
