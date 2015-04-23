package org.weber.siia.channels;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by wilson on 15/4/23.
 */
public class StubEmailConfirmationService implements MessageHandler {
    private List<Email> emails = new ArrayList<Email>();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        SeatConfirmation seatConfirmation = (SeatConfirmation) message.getPayload();
        ChargedBooking chargedBooking = seatConfirmation.getChargedBooking();
        Booking booking = chargedBooking.getBooking();
        Email email = new Email(booking.getCustomerEmail(),
                "Your booking on flight " + booking.getFlightId() + " has been confirmed." +
                        "You have seat number " + seatConfirmation.getSeat().getSeatNumber());
        this.sendEmail(email);
    }

    public void sendEmail(Email email) {
        this.countDownLatch.countDown();
        emails.add(email);
    }

    public List<Email> getEmails() throws InterruptedException {
        this.countDownLatch.await(5, TimeUnit.SECONDS);
        return this.emails;
    }
}
