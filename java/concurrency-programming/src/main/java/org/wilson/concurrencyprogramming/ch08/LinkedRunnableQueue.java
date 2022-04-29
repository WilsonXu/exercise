package org.wilson.concurrencyprogramming.ch08;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue{
    private final int limit;
    private final DenyPolicy denyPolicy;
    private final ThreadPool threadPool;
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (this.runnableList) {
            if (this.runnableList.size() >= limit) {
                this.denyPolicy.reject(runnable, this.threadPool);
            } else {
                this.runnableList.addLast(runnable);
                this.runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (this.runnableList) {
            while (this.runnableList.isEmpty()) {
                try {
                    this.runnableList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return this.runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (this.runnableList) {
            return this.runnableList.size();
        }
    }
}
