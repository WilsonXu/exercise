package org.wilson.concurrencyprogramming.ch31;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TryLockExample {
    private final static Object VAL_OBJ = new Object();

    public static void main(String[] args) {
        final var lock = new TryLock();
        final var validation = new ArrayList<Object>();
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                try {
                    if (lock.tryLock()) {
                        System.out.println(Thread.currentThread() + ": get the lock.");
                        if (validation.size() > 1) {
                            throw new IllegalStateException("Validation failed.");
                        }
                        validation.add(TryLockExample.VAL_OBJ);
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    } else {
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (lock.release()) {
                        System.out.println(Thread.currentThread() + ": release the lock.");
                        validation.remove(TryLockExample.VAL_OBJ);
                    }
                }
            }
        }).start());
    }
}
