package org.weber.prospringintegration.channels.executorchannel;

import org.springframework.integration.Message;
import org.springframework.integration.MessageRejectedException;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.stereotype.Component;
import org.weber.prospringintegration.channels.core.Ticket;

/**
 * Created by wilson on 15/5/5.
 */
@Component
public class TicketMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof Ticket) {
            this.handleTicket((Ticket) payload);
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received.");
        }

    }

    private void handleTicket(Ticket ticket) {
        System.out.println("Received ticket - " + ticket.toString());
    }
}
