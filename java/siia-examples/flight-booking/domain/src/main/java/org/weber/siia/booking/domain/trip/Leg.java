package org.weber.siia.booking.domain.trip;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.weber.siia.booking.domain.Location;
import org.weber.siia.booking.domain.binding.JodaDateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by wxu on 4/28/2015.
 */
@XmlRootElement
public class Leg {
    @XmlElement
    @XmlJavaTypeAdapter(JodaDateTimeAdapter.class)
    private DateTime startOfLegDate;

    @XmlElement
    @XmlJavaTypeAdapter(JodaDateTimeAdapter.class)
    private DateTime endOfLegDate;

    @XmlElement
    private Location startLocation;

    @XmlElement
    private Location endLocation;

    private Leg() {
    }

    public Leg(DateTime startOfLegDate, DateTime endOfLegDate, Location startLocation, Location endLocation) {
        this.startOfLegDate = startOfLegDate;
        this.endOfLegDate = endOfLegDate;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public DateTime getStartOfLegDate() {
        return startOfLegDate;
    }

    public DateTime getEndOfLegDate() {
        return endOfLegDate;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leg leg = (Leg) o;

        if (endLocation != null ? !endLocation.equals(leg.endLocation) : leg.endLocation != null) return false;
        if (endOfLegDate != null ? !endOfLegDate.isEqual(leg.endOfLegDate) : leg.endOfLegDate != null) return false;
        if (startLocation != null ? !startLocation.equals(leg.startLocation) : leg.startLocation != null) return false;
        if (startOfLegDate != null ? !startOfLegDate.isEqual(leg.startOfLegDate) : leg.startOfLegDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getStartLocation()).append(getEndLocation())
                .append(getStartOfLegDate()).append(getEndOfLegDate());
        return builder.toHashCode();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[Start date:").append(getStartOfLegDate()).append("][End date:").append(getEndOfLegDate())
                .append("][start location:").append(getStartLocation()).append("][end location:").append(getEndLocation())
                .append("]");
        return buffer.toString();
    }
}
