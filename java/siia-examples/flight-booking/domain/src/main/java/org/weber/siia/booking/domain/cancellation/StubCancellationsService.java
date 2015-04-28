package org.weber.siia.booking.domain.cancellation;

/**
 * Created by wxu on 4/28/2015.
 */
public class StubCancellationsService implements  CancellationsService {
    private static final String CONFIRMED = "*CONFIRMED*";

    @Override
    public CancellationConfirmation cancel(CancellationRequest cancellationRequest) {
        CancellationConfirmation cancellationConfirmation = new CancellationConfirmation();
        cancellationConfirmation.setReservationCode(cancellationRequest.getReservationCode());
        cancellationConfirmation.setConfirmationNumber(CONFIRMED);
        return cancellationConfirmation;
    }
}
