package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExchangeExample5 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeExample5.class);

    public static void main(String[] args) throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        var thread = new Thread(() -> {
            final var stringBuilder = new StringBuilder();
            IntStream.range(0, 10000).forEach(i -> stringBuilder.append("exchanger"));
            try {
                exchanger.exchange(null);
            } catch (InterruptedException e) {
                ExchangeExample5.LOGGER.warn("An interrupt signal was caught when exchanging!");
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
