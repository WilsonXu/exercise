package org.wilson.concurrencyprogramming.ch29;

import java.util.LinkedList;

public class FooEventDrivenExample {
    public static void handleEventA(Event e) {
        System.out.println(e.data().toLowerCase());
    }

    public static void handleEventB(Event e) {
        System.out.println(e.data().toUpperCase());
    }

    public static void main(String[] args) {
        var events = new LinkedList<Event>();
        events.add(new Event("A", "Hello"));
        events.add(new Event("A", "I am Event A"));
        events.add(new Event("B", "I am Event B"));
        events.add(new Event("B", "world"));

        Event e;
        while (!events.isEmpty()) {
            e = events.remove();
            switch (e.type()) {
                case "A" -> FooEventDrivenExample.handleEventA(e);
                case "B" -> FooEventDrivenExample.handleEventB(e);
            }
        }
    }

    record Event(String type, String data) {
    }
}
