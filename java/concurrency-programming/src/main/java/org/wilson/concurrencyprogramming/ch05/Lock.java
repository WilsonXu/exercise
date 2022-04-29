package org.wilson.concurrencyprogramming.ch05;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;
    void unblock();
    List<Thread> getBlockedThreads();
}
