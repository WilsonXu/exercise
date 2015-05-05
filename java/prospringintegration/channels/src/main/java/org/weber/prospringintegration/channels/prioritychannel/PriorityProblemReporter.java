package org.weber.prospringintegration.channels.prioritychannel;

import org.springframework.integration.MessageHeaders;
import org.springframework.integration.message.GenericMessage;
import org.weber.prospringintegration.channels.core.Ticket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxu on 5/5/2015.
 */
public class PriorityProblemReporter extends ProblemReporter {
    @Override
    public void openTicket(Ticket ticket) {
        Map<String, Object> messageHeader = new HashMap<String, Object>();
        messageHeader.put(MessageHeaders.PRIORITY, ticket.getPriority().ordinal());

        this.channel.send(new GenericMessage<Ticket>(ticket, messageHeader));
        System.out.println("Ticket Sent - " + ticket.toString());
    }
}
