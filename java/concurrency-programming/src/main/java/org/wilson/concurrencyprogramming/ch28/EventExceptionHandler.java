package org.wilson.concurrencyprogramming.ch28;

public interface EventExceptionHandler {
    void handle(Throwable cause, EventContext context);
}
