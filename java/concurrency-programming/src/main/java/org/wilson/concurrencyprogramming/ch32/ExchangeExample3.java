package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangeExample3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeExample3.class);

    public static void main(String[] args) throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        var thread = new Thread(() -> {
            try {
                exchanger.exchange(null);
            } catch (InterruptedException e) {
                ExchangeExample3.LOGGER.warn("An interrupt signal was caught!");
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
