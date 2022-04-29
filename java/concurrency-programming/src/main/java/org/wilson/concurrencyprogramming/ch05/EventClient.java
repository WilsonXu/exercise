package org.wilson.concurrencyprogramming.ch05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EventClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventClient.class);

    public static void main(String[] args) {
        EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for ( ; ; ) {
                eventQueue.offer(new EventQueue.Event(){});
            }
        }, "Producer").start();

        new Thread(() -> {
            for ( ; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    EventClient.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }
        }, "Consumer").start();

    }
}
