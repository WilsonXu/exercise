package org.wilson.concurrencyprogramming.ch13;

import org.wilson.concurrencyprogramming.ch23.CountDownLatch;
import org.wilson.concurrencyprogramming.ch23.Latch;

import java.util.stream.IntStream;

public class VolatileTest {
    private static final Latch LATCH = new CountDownLatch(10);
    private static volatile int i =0;

    private static void inc() {
        VolatileTest.i ++;
    }

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> VolatileTest.inc());
                VolatileTest.LATCH.countDown();
            }).start();
        });
        VolatileTest.LATCH.await();
        System.out.println(VolatileTest.i);
    }
}
