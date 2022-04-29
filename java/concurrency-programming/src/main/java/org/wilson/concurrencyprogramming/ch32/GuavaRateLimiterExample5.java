package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class GuavaRateLimiterExample5 {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterExample5.class);

    private static final GuavaRateLimiterTokenBucket BUCKET = new GuavaRateLimiterTokenBucket();

    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(i -> new Thread(GuavaRateLimiterExample5::extracted).start());
    }

    @SuppressWarnings("java:S2189")
    private static void extracted() {
        while (true) {
            try {
                GuavaRateLimiterExample5.BUCKET.bookOrder(orderId -> {
                    if (GuavaRateLimiterExample5.LOGGER.isInfoEnabled()) {
                        GuavaRateLimiterExample5.LOGGER.info("User {}: booked the order and the order ID is : {}", Thread.currentThread(), orderId);
                    }
                });
            } catch (GuavaRateLimiterTokenBucket.NoProductException e) {
                GuavaRateLimiterExample5.LOGGER.warn("All products have already been sold out");
            } catch (GuavaRateLimiterTokenBucket.OrderFailedException e) {
                if (GuavaRateLimiterExample5.LOGGER.isWarnEnabled()) {
                    GuavaRateLimiterExample5.LOGGER.warn("User {}: the order is failed, please try again.", Thread.currentThread());
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                GuavaRateLimiterExample5.LOGGER.error("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
