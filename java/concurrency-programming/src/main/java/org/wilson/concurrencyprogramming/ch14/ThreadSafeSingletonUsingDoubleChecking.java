package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class ThreadSafeSingletonUsingDoubleChecking {
    private final byte[] data = new byte[1024];

    private static ThreadSafeSingletonUsingDoubleChecking instance = null;

    private ThreadSafeSingletonUsingDoubleChecking() {
    }

    @SuppressWarnings("DoubleCheckedLocking")
    public static ThreadSafeSingletonUsingDoubleChecking getInstance() {
        if (null == ThreadSafeSingletonUsingDoubleChecking.instance) {
            synchronized (ThreadSafeSingletonUsingDoubleChecking.class) {
                if (null == ThreadSafeSingletonUsingDoubleChecking.instance) {
                    ThreadSafeSingletonUsingDoubleChecking.instance = new ThreadSafeSingletonUsingDoubleChecking();
                }
            }
        }
        return ThreadSafeSingletonUsingDoubleChecking.instance;
    }
}
