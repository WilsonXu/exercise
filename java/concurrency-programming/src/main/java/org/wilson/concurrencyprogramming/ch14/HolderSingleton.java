package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public final class HolderSingleton {
    private final byte[] data = new byte[1024];

    private HolderSingleton() {
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final HolderSingleton instance = new HolderSingleton();
    }
}
