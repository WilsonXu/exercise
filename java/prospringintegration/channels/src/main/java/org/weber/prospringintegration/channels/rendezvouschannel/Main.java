package org.weber.prospringintegration.channels.rendezvouschannel;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wxu on 5/13/2015.
 */
public class Main {
    public static void main(String[] args) {
        String contextName = "rendezvous-channel.xml";

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();

        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        TicketReceiver ticketReceiver = applicationContext.getBean(TicketReceiver.class);

        Thread thread = new Thread(ticketReceiver);
        thread.start();
        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }
    }
}
