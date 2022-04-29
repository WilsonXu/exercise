package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionExample2.class);

    @SuppressWarnings("java:S2274")
    public static void main(String[] args) throws InterruptedException {
        final var lock = new ReentrantLock();
        final var condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                ConditionExample2.LOGGER.error("Interrupted!", e);
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        assert !lock.isLocked();
        assert !lock.hasQueuedThreads();

        lock.lock();
        try {
            assert lock.hasWaiters(condition);
            assert lock.getWaitQueueLength(condition) == 1;
        } finally {
            lock.unlock();
        }
    }
}
