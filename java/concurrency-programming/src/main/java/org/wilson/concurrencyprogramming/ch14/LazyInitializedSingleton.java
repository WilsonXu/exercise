package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class LazyInitializedSingleton {
    private final byte[] data = new byte[1024];

    private static LazyInitializedSingleton instance = null;

    private LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance() {
        if (null == LazyInitializedSingleton.instance) {
            LazyInitializedSingleton.instance = new LazyInitializedSingleton();
        }
        return LazyInitializedSingleton.instance;
    }
}
