package org.wilson.concurrencyprogramming.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadInterrupted2.class);

    public static void main(String[] args) {
        ThreadInterrupted2.LOGGER.info("The main thread is interrupted? {}", Thread.interrupted());
        Thread.currentThread().interrupt();
        if (ThreadInterrupted2.LOGGER.isInfoEnabled()) {
            ThreadInterrupted2.LOGGER.info("The main thread is interrupted? {}", Thread.currentThread().isInterrupted());
        }
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            if (ThreadInterrupted2.LOGGER.isInfoEnabled()) {
                ThreadInterrupted2.LOGGER.info("I will be interrupted directly.");
            }
            Thread.currentThread().interrupt();
        }
    }
}
