package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class ThreadSafeSingleton {
    private final byte[] data = new byte[1024];

    private static ThreadSafeSingleton instance = null;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (null == ThreadSafeSingleton.instance) {
            ThreadSafeSingleton.instance = new ThreadSafeSingleton();
        }
        return ThreadSafeSingleton.instance;
    }
}
