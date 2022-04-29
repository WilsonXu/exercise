package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CyclicBarrierExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierExample2.class);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final var barrier = new CyclicBarrier(11);
        IntStream.range(0, 10).forEach(i -> new Thread(new Tourist(i, barrier)).start());
        barrier.await();
        CyclicBarrierExample2.LOGGER.info("Tour Guider: all tourists get on the bus");
        barrier.await();
        CyclicBarrierExample2.LOGGER.info("Tour Guider: all tourists get off the bus");
    }


    private record Tourist(int touristId, CyclicBarrier barrier) implements Runnable {

        @Override
        public void run() {
            CyclicBarrierExample2.LOGGER.info("Tourist: {} by bus.", this.touristId);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist: {} get in the bus and wait for other people's arrival.");
            CyclicBarrierExample2.LOGGER.info("Tourist: {} arrival the destination.", this.touristId);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist: {} get off the bus and wait for other people's get-off.");
        }

        private void waitAndPrint(String message) {
            CyclicBarrierExample2.LOGGER.info(message, this.touristId);
            try {
                this.barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                CyclicBarrierExample2.LOGGER.warn("Interrupted or broken!", e);
                Thread.currentThread().interrupt();
            }
        }

        private void spendSeveralSeconds() {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                CyclicBarrierExample2.LOGGER.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
    }

}
