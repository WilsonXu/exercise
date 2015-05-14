package org.weber.prospringintegration.channels.rendezvouschannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wxu on 5/13/2015.
 */
@Component
public class TicketReceiver implements Runnable {
    protected final static int RECEIVE_TIMEOUT = 1000;

    protected RendezvousChannel channel;

    @Value("#{ticketChannel}")
    public void setChannel(RendezvousChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        Message<?> ticketMessage;

        while (true) {
            ticketMessage = this.channel.receive(RECEIVE_TIMEOUT);
            if (ticketMessage != null) {
                this.handleTicket((Ticket) ticketMessage.getPayload());
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void handleTicket(Ticket ticket) {
        System.out.println("Received ticket - " + ticket.toString());

    }
}
