package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

public class PhaserExample4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaserExample4.class);

    public static void main(String[] args) {
        final var phasher = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return  phase >= 1;
            }
        };
        phasher.arrive();
        phasher.arrive();
        if (phasher.getPhase() == 1) {
            PhaserExample4.LOGGER.info("So far, the phase number is 1.");
        }
        if (!phasher.isTerminated()) {
            PhaserExample4.LOGGER.info("The phaser is not terminated now.");
        }
        phasher.arrive();
        phasher.arrive();
        if (phasher.getPhase() < 1) {
            PhaserExample4.LOGGER.info("So far, the phase number is a negative value.");
        }
        if (phasher.isTerminated()) {
            PhaserExample4.LOGGER.info("The phaser is terminated now.");
        }
        phasher.arriveAndAwaitAdvance();
    }
}
