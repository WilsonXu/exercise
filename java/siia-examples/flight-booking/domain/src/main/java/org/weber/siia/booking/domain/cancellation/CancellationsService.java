package org.weber.siia.booking.domain.cancellation;

/**
 * Created by wxu on 4/28/2015.
 */
public interface CancellationsService {
    CancellationConfirmation cancel(CancellationRequest cancellationRequest);
}
