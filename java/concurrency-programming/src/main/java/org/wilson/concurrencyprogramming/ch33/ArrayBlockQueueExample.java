package org.wilson.concurrencyprogramming.ch33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ArrayBlockQueueExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayBlockQueueExample.class);

    public static void main(String[] args) {
        final var queue = new ArrayBlockingQueue<String>(10);
        IntStream.rangeClosed(0, 10).boxed().map(i -> new Thread(() -> ArrayBlockQueueExample.produceData(queue), "P-Thread-" + i)).forEach(Thread::start);
        IntStream.rangeClosed(0, 10).boxed().map(i -> new Thread(() -> ArrayBlockQueueExample.consumeData(queue), "C-Thread-" + i)).forEach(Thread::start);
    }

    @SuppressWarnings("java:S2189")
    private static void produceData(final ArrayBlockingQueue<String> queue) {
        while (true) {
            var data = String.valueOf(System.currentTimeMillis());
            try {
                queue.put(data);
                if (ArrayBlockQueueExample.LOGGER.isInfoEnabled()) {
                    ArrayBlockQueueExample.LOGGER.info("{} produced data: {}", Thread.currentThread(), data);
                }
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (InterruptedException e) {
                ArrayBlockQueueExample.LOGGER.info("Received the interrupt SIGNAL.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @SuppressWarnings("java:S2189")
    private static void consumeData(final ArrayBlockingQueue<String> queue) {
        while (true) {
            try {
                String data = queue.take();
                if (ArrayBlockQueueExample.LOGGER.isInfoEnabled()) {
                    ArrayBlockQueueExample.LOGGER.info("{} consumed data: {}", Thread.currentThread(), data);
                }
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (InterruptedException e) {
                ArrayBlockQueueExample.LOGGER.info("Received the interrupt SIGNAL.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
