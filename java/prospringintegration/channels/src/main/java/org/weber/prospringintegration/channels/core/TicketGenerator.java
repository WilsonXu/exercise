package org.weber.prospringintegration.channels.core;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by wilson on 15/5/5.
 */
@Component
public class TicketGenerator {
    private long nextTicketId;

    public TicketGenerator() {
        this.nextTicketId = 1000l;
    }

    public List<Ticket> createTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        return tickets;
    }

    private Ticket createEmergencyTicket() {
        return createTicket(Ticket.Priority.emergency, "Urgent problem. Fix immediately or revenue will be lost!");
    }

    private Ticket createHighPriorityTicket() {
        return createTicket(Ticket.Priority.high, "Serious issue. Fix immediately.");
    }

    private Ticket createMediumPriorityTicket() {
        return createTicket(Ticket.Priority.medium, "There is an issue; take a look whenever you have time.");
    }

    private Ticket createLowPriorityTicket() {
        return createTicket(Ticket.Priority.low, "Some minor problems have been found.");
    }

    private Ticket createTicket(Ticket.Priority priority, String description) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(this.nextTicketId++);
        ticket.setPriority(priority);
        ticket.setDescription(description);
        ticket.setIssueDateTime(GregorianCalendar.getInstance());
        return ticket;
    }
}
