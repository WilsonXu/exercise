package org.wilson.concurrencyprogramming.ch19;

public interface Future<T> {
    T get() throws InterruptedException;

    @SuppressWarnings("unused")
    boolean done();
}
