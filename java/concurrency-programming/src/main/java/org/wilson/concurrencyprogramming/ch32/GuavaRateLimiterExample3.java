package org.wilson.concurrencyprogramming.ch32;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("UnstableApiUsage")
public class GuavaRateLimiterExample3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaRateLimiterExample3.class);

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2.0);

    @SuppressWarnings({"java:S2189", "InfiniteLoopStatement"})
    public static void main(String[] args) {
        if (GuavaRateLimiterExample3.LOGGER.isInfoEnabled()) {
            GuavaRateLimiterExample3.LOGGER.info("{}", GuavaRateLimiterExample3.RATE_LIMITER.acquire(4));
            GuavaRateLimiterExample3.LOGGER.info("{}", GuavaRateLimiterExample3.RATE_LIMITER.acquire(2));
            GuavaRateLimiterExample3.LOGGER.info("{}", GuavaRateLimiterExample3.RATE_LIMITER.acquire(2));
            GuavaRateLimiterExample3.LOGGER.info("{}", GuavaRateLimiterExample3.RATE_LIMITER.acquire(2));
        }
    }
}
