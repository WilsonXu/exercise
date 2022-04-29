package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class EagerInitializedSingleton {
    private final byte[] data = new byte[1024];

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return  EagerInitializedSingleton.instance;
    }
}
