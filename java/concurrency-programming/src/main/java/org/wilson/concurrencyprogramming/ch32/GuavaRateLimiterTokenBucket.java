package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

public class GuavaRateLimiterTokenBucket {

    private static final int MAX = 100;

    @SuppressWarnings("UnstableApiUsage")
    private final RateLimiter rateLimiter = RateLimiter.create(10.0);
    private final Monitor monitor = new Monitor();
    private final Monitor.Guard guard = this.monitor.newGuard(() -> this.orderId < GuavaRateLimiterTokenBucket.MAX);
    private int orderId;

    public void bookOrder(IntConsumer consumer) throws NoProductException, OrderFailedException {
        if (this.monitor.enterIf(this.guard)) {
            try {
                if (!this.rateLimiter.tryAcquire(90, TimeUnit.MILLISECONDS)) {
                    throw new OrderFailedException("The order is failed, please try again later.");
                } else {
                    this.orderId ++;
                    consumer.accept(this.orderId);
                }
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new NoProductException("No available product now.");
        }
    }

    public static class NoProductException extends Exception {
        public NoProductException(String message) {
            super(message);
        }
    }

    public static class OrderFailedException extends Exception {
        public OrderFailedException(String message) {
            super(message);
        }
    }
}
