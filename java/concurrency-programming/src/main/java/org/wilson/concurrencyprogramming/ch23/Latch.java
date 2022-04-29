package org.wilson.concurrencyprogramming.ch23;

import java.util.concurrent.TimeUnit;

public abstract class Latch {
    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    public abstract void await() throws InterruptedException;

    public abstract void await(TimeUnit timeUnit, long time) throws InterruptedException, WaitTimoutException;

    public abstract void countDown();

    public abstract int getUnarrived();
}
