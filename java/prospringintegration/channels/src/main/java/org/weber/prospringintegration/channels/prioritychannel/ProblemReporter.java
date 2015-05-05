package org.weber.prospringintegration.channels.prioritychannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wilson on 15/5/5.
 */
@Component
public class ProblemReporter {
    protected PriorityChannel channel;

    @Value("#{ticketChannel}")
    public void setchannel(PriorityChannel channel) {
        this.channel = channel;
    }

    public void openTicket(Ticket ticket) {
        this.channel.send(MessageBuilder.withPayload(ticket).build());
        System.out.println("Ticket Sent - " + ticket.toString());
    }
}
