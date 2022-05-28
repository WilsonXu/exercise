package org.wilson.gia.todo;

import org.wilson.gia.todo.utils.CommandLineInput;
import org.wilson.gia.todo.utils.CommandLineInputHandler;

public class ToDoApp {
    public static final char DEFAULT_INPUT = '\u0000';

    public static void main(String[] args) {
        final var commandLineInputHandler = new CommandLineInputHandler();
        var command = ToDoApp.DEFAULT_INPUT;
        while(CommandLineInput.EXIT.getShortCmd() != command) {
            commandLineInputHandler.printOptions();
            final var input = commandLineInputHandler.readInput();
            final char[] inputChars = input.length() == 1 ? input.toCharArray() : new char[] {ToDoApp.DEFAULT_INPUT};
            command = inputChars[0];
            commandLineInputHandler.processInput(CommandLineInput.getCommandLineInputForInput(command));
        }
    }
}
