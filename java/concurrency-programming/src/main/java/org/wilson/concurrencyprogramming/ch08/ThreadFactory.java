package org.wilson.concurrencyprogramming.ch08;

public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
