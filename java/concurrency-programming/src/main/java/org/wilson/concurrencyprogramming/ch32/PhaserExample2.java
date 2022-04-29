package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaserExample2.class);

    public static void main(String[] args) throws InterruptedException {
        final var phasher = new Phaser();
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
                phasher.register();
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    phasher.arriveAndAwaitAdvance();
                    if (PhaserExample2.LOGGER.isInfoEnabled()) {
                        PhaserExample2.LOGGER.info("{}: {} completed the work.", new Date(), Thread.currentThread());
                    }
                } catch (InterruptedException e) {
                    PhaserExample2.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }, "T-" + i).start()
        );

        TimeUnit.SECONDS.sleep(10);
        phasher.register();
        phasher.arriveAndAwaitAdvance();
        if (PhaserExample2.LOGGER.isInfoEnabled()) {
            PhaserExample2.LOGGER.info("{}: all sub tasks completed work.", new Date());
        }
    }
}
