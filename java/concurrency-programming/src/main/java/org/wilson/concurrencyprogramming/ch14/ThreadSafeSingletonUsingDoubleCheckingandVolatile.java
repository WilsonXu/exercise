package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class ThreadSafeSingletonUsingDoubleCheckingandVolatile {
    private final byte[] data = new byte[1024];

    private volatile static ThreadSafeSingletonUsingDoubleCheckingandVolatile instance = null;

    private ThreadSafeSingletonUsingDoubleCheckingandVolatile() {
    }

    public static ThreadSafeSingletonUsingDoubleCheckingandVolatile getInstance() {
        if (null == ThreadSafeSingletonUsingDoubleCheckingandVolatile.instance) {
            synchronized (ThreadSafeSingletonUsingDoubleCheckingandVolatile.class) {
                if (null == ThreadSafeSingletonUsingDoubleCheckingandVolatile.instance) {
                    ThreadSafeSingletonUsingDoubleCheckingandVolatile.instance = new ThreadSafeSingletonUsingDoubleCheckingandVolatile();
                }
            }
        }
        return ThreadSafeSingletonUsingDoubleCheckingandVolatile.instance;
    }
}
