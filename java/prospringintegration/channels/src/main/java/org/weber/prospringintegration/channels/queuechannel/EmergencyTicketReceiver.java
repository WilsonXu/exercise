package org.weber.prospringintegration.channels.queuechannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.weber.prospringintegration.channels.core.Ticket;

import java.util.List;

/**
 * Created by wxu on 5/5/2015.
 */
@Component
public class EmergencyTicketReceiver extends TicketReceiver {
    private EmergencyTicketSelector emergencyTicketSelector;

    @Autowired
    public void setEmergencyTicketSelector(EmergencyTicketSelector emergencyTicketSelector) {
        this.emergencyTicketSelector = emergencyTicketSelector;
    }

    @Override
    protected void handleTicketMessage() {
        Message<?> ticketMessage;

        while (true) {
            List<Message<?>> emergencyTicketMessage = this.channel.purge(this.emergencyTicketSelector);
            this.handleEmergencyTickets(emergencyTicketMessage);

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

    private void handleEmergencyTickets(List<Message<?>> highPriorityTicketMessages) {
        Assert.notNull(highPriorityTicketMessages);
        for (Message<?> ticketMessage : highPriorityTicketMessages) {
            this.handleTicket((Ticket) ticketMessage.getPayload());
        }
    }
}
