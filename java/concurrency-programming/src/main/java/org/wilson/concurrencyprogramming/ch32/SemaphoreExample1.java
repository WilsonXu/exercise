package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreExample1.class);

    public static void main(String[] args) {
        final var MAX_PERMITS = 10;
        final var loginService = new LoginService(MAX_PERMITS);
        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            boolean login = loginService.login();
            if (!login) {
                if (SemaphoreExample1.LOGGER.isInfoEnabled()) {
                    SemaphoreExample1.LOGGER.info("{} is refused due to executed max online accounts.", Thread.currentThread());
                }
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                SemaphoreExample1.LOGGER.warn("Interrupted!");
                Thread.currentThread().interrupt();
            } finally {
                loginService.logout();
            }
        }, "User-" + i).start()
        );
    }

    private static class LoginService {
        private final Semaphore semaphore;

        public LoginService(int maxPermits) {
            this.semaphore = new Semaphore(maxPermits, true);
        }

        public boolean login() {
            var login = this.semaphore.tryAcquire();
            if (login && SemaphoreExample1.LOGGER.isInfoEnabled()) {
                SemaphoreExample1.LOGGER.info("{} login successfully.", Thread.currentThread());
            }
            return login;
        }

        public void logout() {
            this.semaphore.release();
            if (SemaphoreExample1.LOGGER.isInfoEnabled()) {
                SemaphoreExample1.LOGGER.info("{} logout successfully.", Thread.currentThread());
            }
        }
    }
}
