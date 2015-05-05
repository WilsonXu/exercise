package org.weber.prospringintegration.channels.queuechannel;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessageSelector;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wxu on 5/5/2015.
 */
@Component
public class EmergencyTicketSelector implements MessageSelector {
    @Override
    public boolean accept(Message<?> message) {
        return ((Ticket)message.getPayload()).getPriority() != Ticket.Priority.emergency;
    }
}
