package org.wilson.concurrencyprogramming.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadIsInterrupted1.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                //Do Nothing
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        if (ThreadIsInterrupted1.LOGGER.isInfoEnabled()) {
            ThreadIsInterrupted1.LOGGER.info("The thread is interrupted? {}", thread.isInterrupted());
        }
        thread.interrupt();
        if (ThreadIsInterrupted1.LOGGER.isInfoEnabled()) {
            ThreadIsInterrupted1.LOGGER.info("The thread is interrupted? {}", thread.isInterrupted());
        }
    }
}
