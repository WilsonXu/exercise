package org.wilson.concurrencyprogramming.ch25;

public class Reference {
    private final byte [] data = new byte[2 << 19];

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() {
        System.out.println("The reference will be GC.");
    }
}
