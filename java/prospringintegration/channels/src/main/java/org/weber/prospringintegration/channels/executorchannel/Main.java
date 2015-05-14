package org.weber.prospringintegration.channels.executorchannel;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.weber.prospringintegration.channels.core.Ticket;
import org.weber.prospringintegration.channels.core.TicketGenerator;

import java.util.List;

/**
 * Created by wilson on 15/5/5.
 */
public class Main {
    public static void main(String[] args) {
        String contextName = "executor-channel.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextName);
        applicationContext.start();
        ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
        TicketGenerator ticketGenerator = applicationContext.getBean(TicketGenerator.class);
        TicketMessageHandler ticketMessageHandler = applicationContext.getBean(TicketMessageHandler.class);

        ExecutorChannel executorChannel = applicationContext.getBean("ticketChannel", ExecutorChannel.class);
        executorChannel.subscribe(ticketMessageHandler);

        List<Ticket> tickets = ticketGenerator.createTickets();
        for (Ticket ticket : tickets) {
            problemReporter.openTicket(ticket);
        }
    }
}
