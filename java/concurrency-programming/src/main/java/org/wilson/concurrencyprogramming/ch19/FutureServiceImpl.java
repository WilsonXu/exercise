package org.wilson.concurrencyprogramming.ch19;

import java.util.concurrent.atomic.AtomicInteger;

public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger();

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            future.finish(null);
        }, FutureServiceImpl.this.getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        return this.submit(task, input, null);
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            future.finish(result);
            if (callback != null) {
                callback.call(result);
            }
        }, FutureServiceImpl.this.getNextName()).start();
        return future;
    }

    private String getNextName() {
        return FutureServiceImpl.FUTURE_THREAD_PREFIX + this.nextCounter.getAndIncrement();
    }
}
