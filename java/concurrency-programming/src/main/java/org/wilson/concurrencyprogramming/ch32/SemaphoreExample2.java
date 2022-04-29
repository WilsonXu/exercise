package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreExample2.class);

    public static void main(String[] args) {
        final var MAX_PERMITS = 10;
        final var loginService = new LoginService(MAX_PERMITS);
        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            boolean login = loginService.login();
            if (!login) {
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                SemaphoreExample2.LOGGER.warn("Interrupted!");
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
            try {
                this.semaphore.acquire();
                if (SemaphoreExample2.LOGGER.isInfoEnabled()) {
                    SemaphoreExample2.LOGGER.info("{} login successfully.", Thread.currentThread());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }

        public void logout() {
            this.semaphore.release();
            if (SemaphoreExample2.LOGGER.isInfoEnabled()) {
                SemaphoreExample2.LOGGER.info("{} logout successfully.", Thread.currentThread());
            }
        }
    }
}
