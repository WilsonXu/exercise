package org.weber.prospringintegration.channels.messagingtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wxu on 5/14/2015.
 */
@Component
public class ProblemReporter {
    private MessagingTemplate messagingTemplate;

    @Autowired

    public void setMessagingTemplate(MessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void openTicket(Ticket ticket) {
        messagingTemplate.convertAndSend(ticket);
        System.out.println("Ticket Sent - " + ticket.toString());
    }
}
