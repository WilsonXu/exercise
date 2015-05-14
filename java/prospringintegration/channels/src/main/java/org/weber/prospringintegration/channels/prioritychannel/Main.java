package org.weber.prospringintegration.channels.prioritychannel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wxu on 5/13/2015.
 */
public class Main {
    public static void main(String[] args) {
        String contextName = "priority-channel.xml";

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();

        PriorityProblemReporter priorityProblemReporter = applicationContext.getBean(PriorityProblemReporter.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        PriorityTicketReceiver priorityTicketReceiver = applicationContext.getBean(PriorityTicketReceiver.class);

        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            priorityProblemReporter.openTicket(ticket);
        }
        Thread thread = new Thread(priorityTicketReceiver);
        thread.start();
    }
}
