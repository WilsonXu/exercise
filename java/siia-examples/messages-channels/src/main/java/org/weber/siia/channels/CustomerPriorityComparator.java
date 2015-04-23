package org.weber.siia.channels;

import java.util.Comparator;

/**
 * Created by wilson on 15/4/24.
 */
public class CustomerPriorityComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking left, Booking right) {
        return left.getCustomerEmail().compareTo(right.getCustomerEmail());
    }
}
