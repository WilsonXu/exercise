package org.wilson.concurrencyprogramming.ch05;

public class TestWait2 {
    private final Object mutex = new Object();

    @SuppressWarnings("java:S2274")
    private synchronized void testWait() throws InterruptedException {
        this.mutex.wait();
    }

    public static void main(String[] args) throws InterruptedException {
        var testWait = new TestWait2();
        testWait.testWait();
    }

}
