package org.wilson.concurrencyprogramming.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadIsInterrupted2.class);

    @SuppressWarnings("java:S2142")
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    if (ThreadIsInterrupted2.LOGGER.isInfoEnabled()) {
                        ThreadIsInterrupted2.LOGGER.info("I am be interrupted? {}", Thread.currentThread().isInterrupted());
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        if (ThreadIsInterrupted2.LOGGER.isInfoEnabled()) {
            ThreadIsInterrupted2.LOGGER.info("The thread is interrupted? {}", thread.isInterrupted());
        }
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        if (ThreadIsInterrupted2.LOGGER.isInfoEnabled()) {
            ThreadIsInterrupted2.LOGGER.info("The thread is interrupted? {}", thread.isInterrupted());
        }
    }
}
