package org.wilson.gia.todo.utils;

import org.wilson.gia.todo.model.ToDoItem;
import org.wilson.gia.todo.repository.InMemoryToDoRepository;
import org.wilson.gia.todo.repository.ToDoRepository;

public class CommandLineInputHandler {
    private ToDoRepository toDoRepository = new InMemoryToDoRepository();

    public void printOptions() {
        System.out.println("\n--- To Do Application ---");
        System.out.println("Please make a choice:");
        System.out.println("(a)ll items");
        System.out.println("(f)ind a specific item");
        System.out.println("(i)nsert a new item");
        System.out.println("(u)pdate an existing item");
        System.out.println("(d)elete an existing item");
        System.out.println("(e)xit");
    }

    public String readInput() {
        return System.console().readLine("> ");
    }

    public void processInput(CommandLineInput input) {
        if (input == null) {
            this.handleUnknownInput();
        } else {
            switch (input) {
                case FIND_ALL:
                    this.printAllToDoItems();
                    break;
                case FIND_BY_ID:
                    this.printToDoItem();
                    break;
                case INSERT:
                    this.insertToDoItem();
                    break;
                case UPDATE:
                    this.updateToDoItem();
                    break;
                case DELETE:
                    this.deleteToDoItem();
                    break;
                case EXIT:
                    break;
                default:
                    this.handleUnknownInput();;
            }
        }
    }

    private void printAllToDoItems() {
        final var toDoItems = this.toDoRepository.findAll();
        if (toDoItems.isEmpty()) {
            System.out.println("Nothing to do. Go relax!");
        } else {
            toDoItems.stream().forEach(System.out::println);
        }
    }

    private void printToDoItem() {
        var toDoItem = this.findToDoItem();
        if (toDoItem != null) {
            System.out.println(toDoItem);
        }
    }

    private ToDoItem findToDoItem() {
        Long id = this.askForItemId();
        final var toDoItem = this.toDoRepository.findById(id);

        if (toDoItem == null) {
            System.err.println("To do item with ID " + id + " could not be found.");
        }
        return toDoItem;
    }

    private Long askForItemId() {
        System.out.println("Please enter the item ID:");
        final var input = this.readInput();
        return Long.parseLong(input);
    }

    private void insertToDoItem() {
        final var toDoItem = this.askForNewToDoAction();
        Long id = this.toDoRepository.insert(toDoItem);
        System.out.println("Successfully inserted to do item with ID " + id + ".");
    }

    private ToDoItem askForNewToDoAction() {
        final var toDoItem = new ToDoItem();
        System.out.println("Please enter the name of the item:");
        toDoItem.setName(readInput());
        return toDoItem;
    }

    private void updateToDoItem() {
        final var toDoItem = this.findToDoItem();
        if (toDoItem != null) {
            System.out.println(toDoItem);
            System.out.println("Please enter the name of the item:");
            toDoItem.setName(readInput());
            System.out.println("Please enter the done status the item:");
            toDoItem.setCompleted(Boolean.parseBoolean(readInput()));
            this.toDoRepository.update(toDoItem);
            System.out.println("Successfully updated to do item with ID " + toDoItem.getId() + ".");
        }
    }

    private void deleteToDoItem() {
        final var toDoItem = this.findToDoItem();
        if (toDoItem != null) {
            this.toDoRepository.delete(toDoItem);
            System.out.println("Successfully deleted to do item with ID " + toDoItem.getId() + ".");
        }
    }

    private void handleUnknownInput() {
        System.out.println("Please select a valid option!");
    }
}
