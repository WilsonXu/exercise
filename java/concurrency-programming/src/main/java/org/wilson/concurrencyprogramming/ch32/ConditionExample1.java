package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionExample1.class);

    private static int shareDatta = 0;
    private static boolean dataUsed = false;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            for ( ; ; ) {
                ConditionExample1.change();
            }
        }).start();
        new Thread(() -> {
            for ( ; ; ) {
                ConditionExample1.use();
            }
        }).start();
    }

    private static void change() {
        ConditionExample1.lock.lock();
        try {
            while (!ConditionExample1.dataUsed) {
                ConditionExample1.condition.await();
            }
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            ConditionExample1.shareDatta ++;
            ConditionExample1.dataUsed = false;
            ConditionExample1.LOGGER.info("Produce the new value: {}", ConditionExample1.shareDatta);
            ConditionExample1.condition.signal();
        } catch (InterruptedException e) {
            ConditionExample1.LOGGER.error("Interrupted!", e);
            Thread.currentThread().interrupt();
        } finally {
            ConditionExample1.lock.unlock();
        }
    }

    private static void use() {
        ConditionExample1.lock.lock();
        try {
            while (ConditionExample1.dataUsed) {
                ConditionExample1.condition.await();
            }
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            ConditionExample1.dataUsed = true;
            ConditionExample1.LOGGER.info("The shared data is changed to: {}", ConditionExample1.shareDatta);
            ConditionExample1.condition.signal();
        } catch (InterruptedException e) {
            ConditionExample1.LOGGER.error("Interrupted!", e);
            Thread.currentThread().interrupt();
        } finally {
            ConditionExample1.lock.unlock();
        }
    }
}
