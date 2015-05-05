package org.weber.prospringintegration.channels.queuechannel;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wilson on 15/5/5.
 */
public class EmergencyTicketMain {
    public static void main(String[] args) {
        String contextName = "queue-channel-emergency-handling.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();
        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        EmergencyTicketReceiver ticketReceiver = applicationContext.getBean(EmergencyTicketReceiver.class);

        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }

        Thread consumerThread = new Thread(ticketReceiver);
        consumerThread.start();
    }
}
