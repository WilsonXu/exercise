package org.wilson.concurrencyprogramming.ch08;

public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (this.running && !Thread.currentThread().isInterrupted()) {
            try {
                this.runnableQueue.take().run();
            } catch (InterruptedException e) {
                this.running = false;
                break;
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
