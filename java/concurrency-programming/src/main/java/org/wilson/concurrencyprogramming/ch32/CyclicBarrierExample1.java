package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CyclicBarrierExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierExample1.class);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final var products = CyclicBarrierExample1.getProductsByCategoryId();
        final var barrier = new CyclicBarrier(products.length + 1);
        var list = Arrays.stream(products).mapToObj(CountDownLatchExample.ProductPrice::new).toList();
        list.forEach(pp -> new Thread(() -> {
            if (CyclicBarrierExample1.LOGGER.isInfoEnabled()) {
                CyclicBarrierExample1.LOGGER.info("{} -> start to calculate price.", pp.getProdId());
            }
            try {
                //noinspection DuplicatedCode
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                CyclicBarrierExample1.calculatePrice(pp);
                if (CyclicBarrierExample1.LOGGER.isInfoEnabled()) {
                    CyclicBarrierExample1.LOGGER.info("{} -> the price calculation is completed.", pp.getProdId());
                }
            } catch (InterruptedException e) {
                CyclicBarrierExample1.LOGGER.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            } finally {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    CyclicBarrierExample1.LOGGER.warn("Interrupted or broken!", e);
                    Thread.currentThread().interrupt();
                }
            }
        }).start());
        barrier.await();
        CyclicBarrierExample1.LOGGER.info("All prices are calculated.");
        if (CyclicBarrierExample1.LOGGER.isInfoEnabled()) {
            list.forEach(pp -> CyclicBarrierExample1.LOGGER.info(pp.toString()));
        }
    }

    private static void calculatePrice(CountDownLatchExample.ProductPrice pp) {
        if (pp.getProdId() % 2 == 0) {
            pp.setPrice(pp.getProdId() * 0.9);
        } else {
            pp.setPrice(pp.getProdId() * 0.71);
        }
    }

    private static int[] getProductsByCategoryId() {
        return IntStream.rangeClosed(1, 10).toArray();
    }
}
