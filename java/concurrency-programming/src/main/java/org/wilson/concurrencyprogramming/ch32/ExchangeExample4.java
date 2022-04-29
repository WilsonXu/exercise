package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangeExample4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeExample4.class);

    @SuppressWarnings("java:S2142")
    public static void main(String[] args) throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        var thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                ExchangeExample4.LOGGER.warn("An interrupt signal was caught when sleeping!");
            }
            try {
                exchanger.exchange(null);
            } catch (InterruptedException e) {
                ExchangeExample4.LOGGER.warn("An interrupt signal was caught when exchanging!");
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
