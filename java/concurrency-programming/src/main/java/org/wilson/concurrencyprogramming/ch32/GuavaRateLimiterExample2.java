package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

@SuppressWarnings("UnstableApiUsage")
public class GuavaRateLimiterExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterExample2.class);

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(0.5);

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> new Thread(GuavaRateLimiterExample2::testRateLimiter).start());
    }

    private static void testRateLimiter() {
        double elapsedSeconds = GuavaRateLimiterExample2.RATE_LIMITER.acquire();
        if (GuavaRateLimiterExample2.LOGGER.isInfoEnabled()) {
            GuavaRateLimiterExample2.LOGGER.info("{}: elapsed {} seconds.", Thread.currentThread(), elapsedSeconds);
        }
    }
}
