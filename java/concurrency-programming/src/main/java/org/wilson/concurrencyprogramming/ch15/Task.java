package org.wilson.concurrencyprogramming.ch15;

@FunctionalInterface
public interface Task<T> {
    T call();
}
