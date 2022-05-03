package org.wilson.concurrencyprogramming.ch34;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExecutorExample.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = new ThreadPoolExecutor(2, 4, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.execute(() -> ThreadPoolExecutorExample.LOGGER.info("Execute the runnable task"));
        var future = executor.submit(() -> "Execute the callable task and this is the result");
        if (ThreadPoolExecutorExample.LOGGER.isInfoEnabled()) {
            ThreadPoolExecutorExample.LOGGER.info(future.get());
        }
    }
}
