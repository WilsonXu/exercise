package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockExample1.class);

    public static void main(String[] args) throws InterruptedException {
        final var lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                if (ReentrantLockExample1.LOGGER.isInfoEnabled()) {
                    ReentrantLockExample1.LOGGER.info("{} acquired the lock.", Thread.currentThread());
                    ReentrantLockExample1.printHoldCount(lock);
                }
                lock.lock();
                if (ReentrantLockExample1.LOGGER.isInfoEnabled()) {
                    ReentrantLockExample1.LOGGER.info("{} acquired the lock again.", Thread.currentThread());
                    ReentrantLockExample1.printHoldCount(lock);
                }
            } finally {
                lock.unlock();
                if (ReentrantLockExample1.LOGGER.isInfoEnabled()) {
                    ReentrantLockExample1.LOGGER.info("{} released the lock.", Thread.currentThread());
                    ReentrantLockExample1.printHoldCount(lock);
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        ReentrantLockExample1.LOGGER.info("the main thread acquired the lock.");
        lock.unlock();
        ReentrantLockExample1.LOGGER.info("the main thread released the lock.");
    }

    private static void printHoldCount(ReentrantLock lock) {
        ReentrantLockExample1.LOGGER.info("The hold count of {} is {}", Thread.currentThread(), lock.getHoldCount());
    }
}
