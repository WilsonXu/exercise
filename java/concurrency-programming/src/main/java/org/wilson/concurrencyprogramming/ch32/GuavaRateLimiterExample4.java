package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class GuavaRateLimiterExample4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterExample4.class);

    private static final AtomicInteger DATA =new AtomicInteger();
    private static final GuavaRateLimiterBucket BUCKET = new GuavaRateLimiterBucket();

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                GuavaRateLimiterExample4.BUCKET.submitRequest(GuavaRateLimiterExample4.DATA.getAndIncrement());
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    GuavaRateLimiterExample4.LOGGER.error("Interrupted!", e);
                    Thread.currentThread().interrupt();
                }
            }
        }).start());
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                GuavaRateLimiterExample4.BUCKET.handleRequest(request -> {
                    if (GuavaRateLimiterExample4.LOGGER.isInfoEnabled()) {
                        GuavaRateLimiterExample4.LOGGER.info("Handing {}.", request);
                    }
                });
            }
        }).start());
    }
}
