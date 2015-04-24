package org.weber.siia.channels;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessageDeliveryException;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wilson on 15/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:channels-selector.xml")
public class ChannelSelectorTest {
    @Autowired
    @Qualifier("chargedBookings")
    private MessageChannel chargedBookingsChannel;

    @Autowired
    private StubEmailConfirmationService emailConfirmationService;

    @Test
    public void testSelectorPassing() throws InterruptedException {
        Booking booking = new Booking();
        booking.setFlightId("AC100");
        booking.setCustomerEmail("user@example.com");
        ChargedBooking chargedBooking = new ChargedBooking(booking, 1l);
        Message<ChargedBooking> bookingMessage = MessageBuilder.withPayload(chargedBooking).build();
        this.chargedBookingsChannel.send(bookingMessage);

        Assert.assertEquals(1, this.emailConfirmationService.getEmails().size());
        Assert.assertEquals("user@example.com", this.emailConfirmationService.getEmails().get(0).getRecipient());
    }

    @Test
    public void testSelectorFailing() throws InterruptedException {
        try {
            Booking booking = new Booking();
            booking.setFlightId("AC100");
            booking.setCustomerEmail("user@example.com");
            Message<Booking> bookingMessage = MessageBuilder.withPayload(booking).build();
            this.chargedBookingsChannel.send(bookingMessage);
            Assert.fail();
        } catch (MessageDeliveryException e) {
            Assert.assertTrue(e.getMessage().startsWith("selector"));
            Assert.assertTrue(e.getMessage().contains("org.springframework.integration.selector.PayloadTypeSelector"));
        }
    }
}
