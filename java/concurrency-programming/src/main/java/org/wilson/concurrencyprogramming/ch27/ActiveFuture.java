package org.wilson.concurrencyprogramming.ch27;

import org.wilson.concurrencyprogramming.ch19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
