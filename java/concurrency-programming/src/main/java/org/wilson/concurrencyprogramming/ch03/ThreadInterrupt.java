package org.wilson.concurrencyprogramming.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadInterrupt.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                ThreadInterrupt.LOGGER.info("Oh, I am be interrupted");
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
