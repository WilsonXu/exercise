package org.wilson.concurrencyprogramming.ch32;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample1 {
    private static final StampedLock LOCK = new StampedLock();
    private static int shareData = 0;

    private StampedLockExample1() {
    }

    public static void inc() {
        long stamp = StampedLockExample1.LOCK.writeLock();
        try {
            StampedLockExample1.shareData ++;
        } finally {
            StampedLockExample1.LOCK.unlock(stamp);
        }
    }

    public static int get() {
        long stamp = StampedLockExample1.LOCK.writeLock();
        try {
            return StampedLockExample1.shareData;
        } finally {
            StampedLockExample1.LOCK.unlock(stamp);
        }
    }
}
