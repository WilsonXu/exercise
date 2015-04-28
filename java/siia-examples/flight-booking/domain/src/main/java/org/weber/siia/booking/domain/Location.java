package org.weber.siia.booking.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by wxu on 4/28/2015.
 */
public class Location {
    @XmlAttribute
    private String countryCode;

    @XmlAttribute
    private String city;

    public Location() {
    }

    public Location(String countryCode, String city) {
        this.countryCode = countryCode;
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof  Location)) {
            return  false;
        }
        Location otherLocation = (Location) obj;
        return otherLocation.getCity().equals(this.getCity()) && otherLocation.getCountryCode().equals(this.getCountryCode());
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.getCity()).append(this.getCountryCode());
        return  builder.toHashCode();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCountryCode()).append(" ").append(getCity());
        return buffer.toString();
    }
}
