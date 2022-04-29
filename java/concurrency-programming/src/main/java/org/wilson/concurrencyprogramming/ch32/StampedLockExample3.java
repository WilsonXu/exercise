package org.wilson.concurrencyprogramming.ch32;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample3 {
    private static final StampedLock LOCK = new StampedLock();
    private static int shareData = 0;

    private StampedLockExample3() {
    }

    public static void inc() {
        long stamp = StampedLockExample3.LOCK.writeLock();
        try {
            StampedLockExample3.shareData ++;
        } finally {
            StampedLockExample3.LOCK.unlock(stamp);
        }
    }

    public static int get() {
        long stamp = StampedLockExample3.LOCK.tryOptimisticRead();
        if (!StampedLockExample3.LOCK.validate(stamp)) {
            stamp = StampedLockExample3.LOCK.readLock();
            try {
                return StampedLockExample3.shareData;
            } finally {
                StampedLockExample3.LOCK.unlock(stamp);
            }
        }
        return StampedLockExample3.shareData;
    }
}
