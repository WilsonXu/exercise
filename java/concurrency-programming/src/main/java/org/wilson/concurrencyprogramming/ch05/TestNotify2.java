package org.wilson.concurrencyprogramming.ch05;

public class TestNotify2 {
    private final Object mutex = new Object();

    @SuppressWarnings("java:S2273")
    private void testNotify(){
        this.mutex.notifyAll();
    }

    public static void main(String[] args) {
        var testNotify = new TestNotify2();
        testNotify.testNotify();
    }

}
