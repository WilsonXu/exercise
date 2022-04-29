package org.wilson.concurrencyprogramming.ch19;

public class FutureTask<T> implements Future<T>{

    private T result;
    private boolean isDone = false;
    private final Object lock = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.isDone) {
                this.lock.wait();
            }
            return this.result;
        }
    }

    protected void finish(T result) {
        synchronized (this.lock) {
            if (this.isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            this.lock.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return this.isDone;
    }
}
