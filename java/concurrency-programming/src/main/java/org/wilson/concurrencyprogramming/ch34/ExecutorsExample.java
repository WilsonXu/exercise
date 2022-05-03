package org.wilson.concurrencyprogramming.ch34;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ExecutorsExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorsExample.class);

    @SuppressWarnings("java:S1215")
    public static void main(String[] args) throws InterruptedException {
        ExecutorsExample.singleThreadPool();
        ExecutorsExample.printThreadStack();
        if (ExecutorsExample.LOGGER.isInfoEnabled()) {
            ExecutorsExample.LOGGER.info("*****************************************");
        }
        System.gc();
        TimeUnit.MINUTES.sleep(1);
        ExecutorsExample.printThreadStack();
    }

    private static void printThreadStack() {
        if (ExecutorsExample.LOGGER.isInfoEnabled()) {
            final var threadMXBean = ManagementFactory.getThreadMXBean();
            Stream.of(threadMXBean.getAllThreadIds()).forEach(id -> ExecutorsExample.LOGGER.info(Arrays.toString(threadMXBean.getThreadInfo(id))));
        }
    }

    private static void singleThreadPool() {
        if (ExecutorsExample.LOGGER.isInfoEnabled()) {
            Executors.newSingleThreadExecutor().execute(() -> ExecutorsExample.LOGGER.info("Normal task."));
        }
    }
}
