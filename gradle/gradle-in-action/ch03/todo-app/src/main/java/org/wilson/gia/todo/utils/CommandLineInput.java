package org.wilson.gia.todo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum CommandLineInput {
    FIND_ALL('a'), FIND_BY_ID('f'), INSERT('i'), UPDATE('u'), DELETE('d'), EXIT('e');

    private final static Map<Character, CommandLineInput> INPUTS = new HashMap<>();

    static {
        Stream.of(CommandLineInput.values()).forEach(value -> CommandLineInput.INPUTS.put(value.getShortCmd(), value));
    }

    public static CommandLineInput getCommandLineInputForInput(char input) {
        return CommandLineInput.INPUTS.get(input);
    }

    private final char shortCmd;

    private CommandLineInput(char shortCmd) {
        this.shortCmd = shortCmd;
    }

    public char getShortCmd() {
        return this.shortCmd;
    }
}
