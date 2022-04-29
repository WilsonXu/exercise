package org.wilson.concurrencyprogramming.ch29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncChannel implements Channel<Event>{
    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public final void dispatch(Event message) {
        this.executorService.submit(() -> this.handle(message));
    }

    protected abstract void handle(Event message);

    void stop() {
        if (null != this.executorService && !this.executorService.isShutdown()) {
            this.executorService.shutdown();
        }
    }
}
