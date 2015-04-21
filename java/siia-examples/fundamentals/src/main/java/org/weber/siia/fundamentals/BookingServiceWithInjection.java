package org.weber.siia.fundamentals;

import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Created by wilson on 15/4/21.
 */
public class BookingServiceWithInjection {
    private final BookingDao bookingDao;
    private final WebServiceOperations mealPreferenceWebServiceInvoker;

    public BookingServiceWithInjection(BookingDao bookingDao, WebServiceOperations mealPreferenceWebServiceInvoker) {
        this.bookingDao = bookingDao;
        WebServiceOperations mealPreferenceWebServiceInvoker1 = mealPreferenceWebServiceInvoker;
        this.mealPreferenceWebServiceInvoker = mealPreferenceWebServiceInvoker1;
    }

    public void updateMeal(MealPreference mealPreference) {
        Booking booking = this.bookingDao.getBookById(mealPreference.getBookingId());
        Source mealUpdateSource = this.buildMealPreferenceUpdateRequest(booking, mealPreference);
        StringResult result = new StringResult();
        this.mealPreferenceWebServiceInvoker.sendSourceAndReceiveToResult(mealUpdateSource, result);
    }

    public Source buildMealPreferenceUpdateRequest(Booking booking, MealPreference mealPreference) {
        return new StringSource(
                "<updateMealPreference>" +
                        "<flightRef>" +
                        booking.getFlightRef() +
                        "</flightRef>" +
                        "<mealPreference>" +
                        mealPreference +
                        "</mealPreference>" +
                        "</updateMealPreference>");
    }
}
