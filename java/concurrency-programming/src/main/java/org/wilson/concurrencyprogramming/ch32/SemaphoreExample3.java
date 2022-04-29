package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreExample3.class);

    public static void main(String[] args) {
        final var tryLock = new TryLock();
        new Thread(() -> {
            var gotLock = tryLock.tryLock();
            if (!gotLock) {
                if (SemaphoreExample3.LOGGER.isInfoEnabled()) {
                    SemaphoreExample3.LOGGER.info("{} can't the lock and will do other thing.", Thread.currentThread());
                }
                return;
            }
            try {
                SemaphoreExample3.simulateWork();
            } catch (InterruptedException e) {
                SemaphoreExample3.LOGGER.warn("Interrupted!");
                Thread.currentThread().interrupt();
            } finally {
                tryLock.unlock();
            }
        }).start();

        var gotLock = tryLock.tryLock();
        if (!gotLock) {
            if (SemaphoreExample3.LOGGER.isInfoEnabled()) {
                SemaphoreExample3.LOGGER.info("{} can't the lock and will do other thing.", Thread.currentThread());
            }
        } else {
            try {
                SemaphoreExample3.simulateWork();
            } catch (InterruptedException e) {
                SemaphoreExample3.LOGGER.warn("Interrupted!");
                Thread.currentThread().interrupt();
            } finally {
                tryLock.unlock();
            }
        }
    }


    private static void simulateWork() throws InterruptedException {
        if (SemaphoreExample3.LOGGER.isInfoEnabled()) {
            SemaphoreExample3.LOGGER.info("{} get the lock and do working...", Thread.currentThread());
        }
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
    }

    private static class TryLock {
        private final Semaphore semaphore = new Semaphore(1);

        public boolean tryLock() {
            return this.semaphore.tryAcquire();
        }

        public void unlock() {
            this.semaphore.release();
            if (SemaphoreExample3.LOGGER.isInfoEnabled()) {
                SemaphoreExample3.LOGGER.info("{} release the lock", Thread.currentThread());
            }
        }
    }
}
