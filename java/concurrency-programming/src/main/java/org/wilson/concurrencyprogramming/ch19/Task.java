package org.wilson.concurrencyprogramming.ch19;

@FunctionalInterface
public interface Task<IN, OUT> {
    OUT get(IN input);
}
