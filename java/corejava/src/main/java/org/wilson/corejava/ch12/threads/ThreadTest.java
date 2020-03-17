package org.wilson.corejava.ch12.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
    public static final Logger LOGGER = LoggerFactory.getLogger("org.wilson.corejava.ch12.threads.ThreadTest");

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 100000);
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0, 1, amount);
                    Thread.sleep((long) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        };
        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((long) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
