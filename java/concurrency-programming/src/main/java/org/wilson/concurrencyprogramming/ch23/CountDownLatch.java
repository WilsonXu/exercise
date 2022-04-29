package org.wilson.concurrencyprogramming.ch23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch{
    private Runnable runnable;

    public CountDownLatch(int limit) {
        this(limit, null);
    }

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit);
        this.runnable = runnable;
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (this.limit > 0) {
                this.wait();
            }
        }
        if (this.runnable != null) {
            this.runnable.run();
        }
    }

    @Override
    public void await(TimeUnit timeUnit, long time) throws InterruptedException, WaitTimoutException {
        if (time <=0) {
            throw new IllegalArgumentException("The time is invalid.");
        }
        long remainingNanos = timeUnit.toNanos(time);
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (this.limit > 0) {
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimoutException("The waiting time is over the specify time");
                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
        if (this.runnable != null) {
            this.runnable.run();
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (this.limit < 0) {
                throw new IllegalStateException("All of tasks have already been arrived");
            }
            this.limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return this.limit;
    }
}
