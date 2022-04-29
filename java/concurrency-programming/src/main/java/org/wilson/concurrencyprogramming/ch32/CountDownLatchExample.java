package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CountDownLatchExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountDownLatchExample.class);

    public static void main(String[] args) throws InterruptedException {
        final var products = CountDownLatchExample.getProductsByCategoryId();
        final var latch = new CountDownLatch(products.length);
        var list = Arrays.stream(products).mapToObj(ProductPrice::new).toList();
        list.forEach(pp -> new Thread(() -> {
            if (CountDownLatchExample.LOGGER.isInfoEnabled()) {
                CountDownLatchExample.LOGGER.info("{} -> start to calculate price.", pp.getProdId());
            }
            try {
                //noinspection DuplicatedCode
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                CountDownLatchExample.calculatePrice(pp);
                if (CountDownLatchExample.LOGGER.isInfoEnabled()) {
                    CountDownLatchExample.LOGGER.info("{} -> the price calculation is completed.", pp.getProdId());
                }
            } catch (InterruptedException e) {
                CountDownLatchExample.LOGGER.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }).start());
        latch.await();
        CountDownLatchExample.LOGGER.info("All prices are calculated.");
        if (CountDownLatchExample.LOGGER.isInfoEnabled()) {
            list.forEach(pp -> CountDownLatchExample.LOGGER.info(pp.toString()));
        }
    }

    private static void calculatePrice(ProductPrice pp) {
        if (pp.getProdId() % 2 == 0) {
            pp.setPrice(pp.getProdId() * 0.9);
        } else {
            pp.setPrice(pp.getProdId() * 0.71);
        }
    }

    private static int[] getProductsByCategoryId() {
        return IntStream.rangeClosed(1, 10).toArray();
    }

    public static class ProductPrice {
        private final int prodId;
        private double price;

        public ProductPrice(int prodId) {
            this(prodId, -1);
        }

        public ProductPrice(int prodId, double price) {
            this.prodId = prodId;
            this.price = price;
        }

        public int getProdId() {
            return prodId;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ProductPrice{" +
                    "ProdId=" + prodId +
                    ", price=" + price +
                    '}';
        }
    }
}
