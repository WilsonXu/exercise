package org.wilson.concurrencyprogramming.ch05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest2 {
    private final Lock lock = new BooleanLock();

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest2 task = new BooleanLockTest2();

        new Thread(task::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2 = new Thread(task::syncMethod, "T1");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);

        t2.interrupt();
    }

    public void syncMethod() {
        try {
            this.lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unblock();
        }
    }
}
