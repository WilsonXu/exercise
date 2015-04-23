package org.weber.siia.channels;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wilson on 15/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:channels-all-direct.xml")
public class AllChannelsDirectTest {
    @Autowired
    @Qualifier("bookingConfirmationRequests")
    private MessageChannel bookingChannel;

    @Autowired
    private StubEmailConfirmationService emailConfirmationService;

    @Test
    public void testChannels() throws InterruptedException {
        Booking booking = new Booking();
        booking.setFlightId("AC100");
        booking.setCustomerEmail("user@example.com");
        Message<Booking> bookingMessage = MessageBuilder.withPayload(booking).build();
        bookingChannel.send(bookingMessage);

        Assert.assertEquals(1, this.emailConfirmationService.getEmails().size());
        Assert.assertEquals("user@example.com", this.emailConfirmationService.getEmails().get(0).getRecipient());
    }

}
