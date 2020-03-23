package org.wilson.corejava.v1.ch12.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Created by wilson on 2020/3/23.
 */
@Slf4j
public class ThreadTest {
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 100000);

        Runnable task1 = () -> IntStream.range(0, STEPS).forEach(i -> {
            double amount = MAX_AMOUNT * Math.random();
            bank.transfer(0, 1, amount);
            try {
                Thread.sleep((long) (DELAY * Math.random()));
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        Runnable task2 = () -> IntStream.range(0, STEPS).forEach(i -> {
            double amount = MAX_AMOUNT * Math.random();
            bank.transfer(2, 3, amount);
            try {
                Thread.sleep((long) (DELAY * Math.random()));
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
