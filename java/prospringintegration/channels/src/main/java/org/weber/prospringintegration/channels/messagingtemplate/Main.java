package org.weber.prospringintegration.channels.messagingtemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wxu on 5/14/2015.
 */
public class Main {
    public static void main(String[] args) {
        String contextName = "messaging-template.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();

        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketReceiver ticketReceiver = applicationContext.getBean(TicketReceiver.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);

        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }

        Thread thread = new Thread(ticketReceiver);
        thread.start();
    }
}
