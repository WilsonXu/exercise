package org.wilson.concurrencyprogramming.ch05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class EventQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventQueue.class);
    private static final int DEFAULT_MAX_EVENT = 10;

    private final int max;
    private final LinkedList<Event> queue = new LinkedList<>();

    public EventQueue() {
        this(EventQueue.DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (this.queue) {
            while (this.queue.size() >= this.max) {
                try {
                    this.console("The queue is full.");
                    this.queue.wait();
                } catch (InterruptedException e) {
                    EventQueue.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }

            console("The new event is submitted.");
            this.queue.addLast(event);
            this.queue.notifyAll();
        }
    }

    public Event take() {
        synchronized (this.queue) {
            while (this.queue.isEmpty()) {
                try {
                    this.console("The queue is empty.");
                    this.queue.wait();
                } catch (InterruptedException e) {
                    EventQueue.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }

            Event event = this.queue.removeFirst();
            this.queue.notifyAll();
            console("The event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message) {
        if (EventQueue.LOGGER.isInfoEnabled()) {
            EventQueue.LOGGER.info("{}: {}", Thread.currentThread().getName(), message);
        }
    }

    interface Event {
    }
}
