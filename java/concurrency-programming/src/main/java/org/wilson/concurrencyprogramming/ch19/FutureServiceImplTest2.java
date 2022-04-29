package org.wilson.concurrencyprogramming.ch19;

import java.util.concurrent.TimeUnit;

public class FutureServiceImplTest2 {
    public static void main(String[] args) throws InterruptedException {
        Future<Integer> future = FutureService.<String, Integer>newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");
        System.out.println(future.get());
    }
}
