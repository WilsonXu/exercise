package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class GuavaRateLimiterBucket {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterBucket.class);

    private static final  int BUCKET_CAPACITY = 1000;

    private final Queue<Request> container = new ConcurrentLinkedQueue<>();
    @SuppressWarnings("UnstableApiUsage")
    private final RateLimiter rateLimiter = RateLimiter.create(10.0);
    private final Monitor requestMonitor = new Monitor();
    private final Monitor handlerMonitor = new Monitor();
    private final Monitor.Guard requestGuard = this.requestMonitor.newGuard(() -> this.container.size() < GuavaRateLimiterBucket.BUCKET_CAPACITY);
    private final Monitor.Guard handlerGuard = this.handlerMonitor.newGuard(() -> !this.container.isEmpty());

    public void submitRequest(int data) {
        this.submitRequest(new Request(data));
    }

    public void submitRequest(Request request) {
        if (this.requestMonitor.enterIf(this.requestGuard)) {
            try {
                this.container.offer(request);
                if (GuavaRateLimiterBucket.LOGGER.isInfoEnabled()) {
                    GuavaRateLimiterBucket.LOGGER.info("{} submitted request: {} successfully.", Thread.currentThread(), request.data());
                }
            } finally {
                this.requestMonitor.leave();
            }
        } else {
            if (GuavaRateLimiterBucket.LOGGER.isInfoEnabled()) {
                GuavaRateLimiterBucket.LOGGER.info("The request: {} will be down-dimensional handled due to the bucket is overflow.", request.data());
            }
            // produce this one into MQ which will be handled again later.
        }
    }

    public void handleRequest(Consumer<Request> consumer) {
        if (this.handlerMonitor.enterIf(this.handlerGuard)) {
            try {
                //noinspection UnstableApiUsage
                this.rateLimiter.acquire();
                consumer.accept(this.container.poll());
            } finally {
                this.handlerMonitor.leave();
            }
        }
    }

    public record Request(int data) {
        @Override
            public String toString() {
                return "Request{" +
                        "data=" + data +
                        '}';
            }
        }
}
