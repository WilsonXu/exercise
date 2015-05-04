package org.weber.prospringintegration.channels.directchannel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wilson on 15/5/5.
 */
public class Main {
    public static void main(String[] args) {
        String contextName = "direct-channel.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();
        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        TicketMessageHandler ticketMessageHandler = applicationContext.getBean(TicketMessageHandler.class);

        DirectChannel directChannel = applicationContext.getBean("ticketChannel", DirectChannel.class);
        directChannel.subscribe(ticketMessageHandler);

        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }
    }
}
