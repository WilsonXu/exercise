package org.wilson.concurrencyprogramming.ch05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BooleanLockTest3 {
    private final Lock lock = new BooleanLock();

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest3 task = new BooleanLockTest3();

        new Thread(task::syncMethodTimeoutable, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2 = new Thread(task::syncMethodTimeoutable, "T1");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
    }

    public void syncMethodTimeoutable() {
        try {
            this.lock.lock(1000);
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            this.lock.unblock();
        }
    }
}
