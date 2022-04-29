package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockExample3.class);

    public static void main(String[] args) {
        final var accumulator = new Accumulator();
        IntStream.range(0, 10).forEach(i -> new AccumulatorThread(accumulator).start());
    }

    private static class AccumulatorThread extends Thread {
        private final Accumulator accumulator;

        public AccumulatorThread(Accumulator accumulator) {
            this.accumulator = accumulator;
        }

        @Override
        public void run() {
            while (true) {
                this.accumulator.addX();
                this.accumulator.addY();
                if (this.accumulator.getX() != this.accumulator.getY() && ReentrantLockExample3.LOGGER.isErrorEnabled()) {
                    ReentrantLockExample3.LOGGER.error("The x:{} dose not equal y:{}", this.accumulator.getX(), this.accumulator.getY());
                }
            }
        }
    }
    private static class Accumulator {
        private static final Lock lock = new ReentrantLock();

        private int x = 0;
        private int y = 0;

        void addX() {
            Accumulator.lock.lock();
            try {
                this.x ++;
            } finally {
                Accumulator.lock.unlock();
            }
        }

        void addY() {
            Accumulator.lock.lock();
            try {
                this.y ++;
            } finally {
                Accumulator.lock.unlock();
            }
        }

        int getX() {
            Accumulator.lock.lock();
            try {
                return this.x;
            } finally {
                Accumulator.lock.unlock();
            }
        }

        int getY() {
            Accumulator.lock.lock();
            try {
                return this.y;
            } finally {
                Accumulator.lock.unlock();
            }
        }
    }
}
