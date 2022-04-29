package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaserExample3.class);

    public static void main(String[] args) {
        final var phasher = new MyPhaser(() -> {
            if (PhaserExample3.LOGGER.isInfoEnabled()) {
                PhaserExample3.LOGGER.info("{}: all sub tasks completed the work.", new Date());
            }
        });
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
                phasher.register();
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    phasher.arriveAndAwaitAdvance();
                    if (PhaserExample3.LOGGER.isInfoEnabled()) {
                        PhaserExample3.LOGGER.info("{}: {} completed the work.", new Date(), Thread.currentThread());
                    }
                } catch (InterruptedException e) {
                    PhaserExample3.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }, "T-" + i).start()
        );
    }

    private static class MyPhaser extends Phaser {
        private final Runnable runnable;

        public MyPhaser(Runnable runnable) {
            super();
            this.runnable = runnable;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            this.runnable.run();
            return super.onAdvance(phase, registeredParties);
        }
    }
}
