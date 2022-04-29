package org.wilson.concurrencyprogramming.ch05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest1 {
    private final Lock lock = new BooleanLock();

    public static void main(String[] args) {
        BooleanLockTest1 task = new BooleanLockTest1();
        IntStream.range(0, 10).mapToObj(i -> new Thread(task::syncMethod)).forEach(Thread::start);
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
