package org.weber.siia.booking.domain.notifications;

/**
 * Created by wxu on 4/28/2015.
 */
public interface Notifiable<T> {
    void notify(T notification);
}
