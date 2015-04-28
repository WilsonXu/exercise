package org.weber.siia.booking.domain.trip;

import org.weber.siia.booking.domain.Command;

import java.util.Collections;
import java.util.List;

/**
 * Created by wxu on 4/28/2015.
 */
public class CreateTripCommand {
    private final List<Command> subCommands;

    public CreateTripCommand(List<Command> subCommands) {
        this.subCommands = Collections.unmodifiableList(subCommands);
    }

    public List<Command> getSubCommands() {
        return subCommands;
    }
}
