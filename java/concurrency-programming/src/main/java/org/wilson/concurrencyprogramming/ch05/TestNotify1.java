package org.wilson.concurrencyprogramming.ch05;

public class TestNotify1 {
    @SuppressWarnings("java:S2273")
    private void testNotify(){
        this.notifyAll();
    }

    public static void main(String[] args) {
        var testNotify = new TestNotify1();
        testNotify.testNotify();
    }

}
