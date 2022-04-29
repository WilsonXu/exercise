package org.wilson.concurrencyprogramming.ch28;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncEventBus extends EventBus{
    private final static String DEFAULT_ASYNC_BUS_NAME = "default";

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        super(busName, null, executor);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {
        super(AsyncEventBus.DEFAULT_ASYNC_BUS_NAME, null, executor);
    }

    public AsyncEventBus(EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(AsyncEventBus.DEFAULT_ASYNC_BUS_NAME, exceptionHandler, executor);
    }

    public AsyncEventBus(String busName, EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName, exceptionHandler, executor);
    }
}
