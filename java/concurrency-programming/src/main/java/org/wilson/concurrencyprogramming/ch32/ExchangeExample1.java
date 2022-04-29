package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExchangeExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeExample1.class);

    public static void main(String[] args) {
        final var exchanger = new Exchanger<String>();
        ExchangeExample1.generateThread(exchanger, "T1").start();
        ExchangeExample1.generateThread(exchanger, "T2").start();
    }

    private static Thread generateThread(final Exchanger<String> exchanger, final String threadName) {
        return new Thread(() -> {
            try {
                if (ExchangeExample1.LOGGER.isInfoEnabled()) {
                    ExchangeExample1.LOGGER.info("{} starts.", Thread.currentThread());
                }
                ExchangeExample1.randomSleep();
                String data = exchanger.exchange("I am from " + Thread.currentThread().getName());
                if (ExchangeExample1.LOGGER.isInfoEnabled()) {
                    ExchangeExample1.LOGGER.info("{} received: {}", Thread.currentThread().getName(), data);
                    ExchangeExample1.LOGGER.info("{} ends.", Thread.currentThread());
                }
            } catch (InterruptedException e) {
                ExchangeExample1.LOGGER.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }, threadName);
    }

    private static void randomSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
    }
}
