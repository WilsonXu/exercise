package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("UnstableApiUsage")
public class GuavaRateLimiterExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterExample1.class);

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(0.5);

    @SuppressWarnings({"java:S2189", "InfiniteLoopStatement"})
    public static void main(String[] args) {
        for ( ; ; ) {
            GuavaRateLimiterExample1.testRateLimiter();
        }
    }

    private static void testRateLimiter() {
        double elapsedSeconds = GuavaRateLimiterExample1.RATE_LIMITER.acquire();
        if (GuavaRateLimiterExample1.LOGGER.isInfoEnabled()) {
            GuavaRateLimiterExample1.LOGGER.info("{}: elapsed {} seconds.", Thread.currentThread(), elapsedSeconds);
        }
    }
}
