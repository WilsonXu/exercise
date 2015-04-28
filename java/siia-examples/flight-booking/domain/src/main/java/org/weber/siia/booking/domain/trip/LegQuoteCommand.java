package org.weber.siia.booking.domain.trip;

import org.weber.siia.booking.domain.Command;
import org.weber.siia.booking.domain.car.CarCriteria;
import org.weber.siia.booking.domain.flight.FlightCriteria;
import org.weber.siia.booking.domain.hotel.HotelCriteria;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wxu on 4/28/2015.
 */
@XmlRootElement(name = "legQuote")
public class LegQuoteCommand implements Command {
    private Leg leg;
    private HotelCriteria hotelCriteria;
    private FlightCriteria flightCriteria;

    private LegQuoteCommand() {
    }

    public LegQuoteCommand(Leg leg) {
        this.leg = leg;
    }

    private CarCriteria carCriteria;

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public HotelCriteria getHotelCriteria() {
        return hotelCriteria;
    }

    public void setHotelCriteria(HotelCriteria hotelCriteria) {
        this.hotelCriteria = hotelCriteria;
    }

    public FlightCriteria getFlightCriteria() {
        return flightCriteria;
    }

    public void setFlightCriteria(FlightCriteria flightCriteria) {
        this.flightCriteria = flightCriteria;
    }

    public CarCriteria getCarCriteria() {
        return carCriteria;
    }

    public void setCarCriteria(CarCriteria carCriteria) {
        this.carCriteria = carCriteria;
    }
}
