package org.wilson.concurrencyprogramming.ch32;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample2 {
    private static final StampedLock LOCK = new StampedLock();
    private static int shareData = 0;

    private StampedLockExample2() {
    }

    public static void inc() {
        long stamp = StampedLockExample2.LOCK.writeLock();
        try {
            StampedLockExample2.shareData ++;
        } finally {
            StampedLockExample2.LOCK.unlock(stamp);
        }
    }

    public static int get() {
        long stamp = StampedLockExample2.LOCK.readLock();
        try {
            return StampedLockExample2.shareData;
        } finally {
            StampedLockExample2.LOCK.unlock(stamp);
        }
    }
}
