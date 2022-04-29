package org.wilson.concurrencyprogramming.ch19;

@FunctionalInterface
public interface Callback<T> {
    void call(T t);
}
