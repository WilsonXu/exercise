package org.wilson.concurrencyprogramming.ch14;

@SuppressWarnings("unused")
public enum EnumSingleton {
    INSTANCE;

    private final byte[] data = new byte[1024];

    public void doSomething() {
    }
}
