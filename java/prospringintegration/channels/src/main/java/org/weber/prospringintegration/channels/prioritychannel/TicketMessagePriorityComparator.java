package org.weber.prospringintegration.channels.prioritychannel;

import org.springframework.integration.Message;
import org.weber.prospringintegration.channels.core.Ticket;

import java.util.Comparator;

/**
 * Created by wxu on 5/5/2015.
 */
public class TicketMessagePriorityComparator implements Comparator<Message<Ticket>> {
    @Override
    public int compare(Message<Ticket> message1, Message<Ticket> message2) {
        Integer priority1 = message1.getPayload().getPriority().ordinal();
        Integer priority2 = message2.getPayload().getPriority().ordinal();

        priority1 = priority1 != null ? priority1 : 0;
        priority2 = priority2 != null ? priority2 : 0;
        return priority2.compareTo(priority1);
    }
}
