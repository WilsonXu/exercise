package org.weber.prospringintegration.channels.directchannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wilson on 15/5/5.
 */
@Component
public class ProblemReporter {
    private DirectChannel directChannel;

    @Value("#{ticketChannel}")
    public void setDirectChannel(DirectChannel directChannel) {
        this.directChannel = directChannel;
    }

    public void openTicket(Ticket ticket) {
        this.directChannel.send(MessageBuilder.withPayload(ticket).build());
        System.out.println("Ticket Sent - " + ticket.toString());
    }
}
