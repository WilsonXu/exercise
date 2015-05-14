package org.weber.prospringintegration.channels.messagingtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wxu on 5/14/2015.
 */
@Component
public class TicketReceiver implements Runnable {
    private final static int RECEIVE_TIMEOUT = 1000;

    private MessagingTemplate messagingTemplate;

    public TicketReceiver() {
    }

    @Autowired
    public void setMessagingTemplate(MessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.messagingTemplate.setSendTimeout(RECEIVE_TIMEOUT);
    }

    private void handleTicketMessage() {
        Message<?> ticketMessage;

        while (true) {
            ticketMessage = this.messagingTemplate.receive();
            if(ticketMessage != null) {
                this.handleTicket((Ticket) ticketMessage.getPayload());
            }
        }
    }

    private void handleTicket(Ticket ticket) {
        System.out.println("Received ticket - " + ticket.toString());
    }

    @Override
    public void run() {
        this.handleTicketMessage();
    }
}
