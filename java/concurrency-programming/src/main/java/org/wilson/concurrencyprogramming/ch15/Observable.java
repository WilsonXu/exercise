package org.wilson.concurrencyprogramming.ch15;

public interface Observable {
    Cycle getCycle();

    void start();

    void interrupt();

    enum Cycle {
        STARTED, RUNNING, DONE, ERROR;
    }
}
