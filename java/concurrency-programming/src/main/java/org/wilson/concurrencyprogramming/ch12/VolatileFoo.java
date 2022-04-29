package org.wilson.concurrencyprogramming.ch12;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {
    public final static int MAX = 5;
    public static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = VolatileFoo.init_value;
            while (localValue < VolatileFoo.MAX) {
                if (VolatileFoo.init_value != localValue) {
                    System.out.printf("The init_value is updated to [%d]\n", VolatileFoo.init_value);
                    localValue = VolatileFoo.init_value;
                }
            }
        }, "Reader").start();
        new Thread(() -> {
            int localValue = VolatileFoo.init_value;
            while (localValue < VolatileFoo.MAX) {
                System.out.printf("The init_value will be changed to [%d]\n", ++ localValue);
                VolatileFoo.init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "Updater").start();
    }
}
