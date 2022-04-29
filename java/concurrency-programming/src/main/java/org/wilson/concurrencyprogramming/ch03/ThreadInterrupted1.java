package org.wilson.concurrencyprogramming.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadInterrupted1.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                ThreadInterrupted1.LOGGER.info("{}", Thread.interrupted());
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
    }
}
