package org.wilson.concurrencyprogramming.ch19;

import java.util.concurrent.TimeUnit;

public class FutureServiceImplTest1 {
    public static void main(String[] args) throws InterruptedException {
        Future<?> future = FutureService.newService().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finished.");
        });
        future.get();
    }
}
