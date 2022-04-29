package org.wilson.concurrencyprogramming.ch05;

public class TestWait1 {
    @SuppressWarnings({"java:S2274","java:S2273"})
    private void testWait() throws InterruptedException {
        this.wait();
    }

    public static void main(String[] args) throws InterruptedException {
        var testWait = new TestWait1();
        testWait.testWait();
    }

}
