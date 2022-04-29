package org.wilson.concurrencyprogramming.ch15;

import java.util.concurrent.TimeUnit;

public class ObservableThread<T> extends Thread implements Observable{
    private final Task<T> task;
    private final TaskLifecycle<T> lifecycle;
    private Cycle cycle;

    public ObservableThread(Task<T> task)  {
        this(task, new TaskLifecycle.LifecycleAdapter<>());
    }

    public ObservableThread(Task<T> task, TaskLifecycle<T> lifecycle) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("The task is required.");
        }
        this.task = task;
        this.lifecycle = lifecycle;
    }

    @Override
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (this.lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(Thread.currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(Thread.currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(Thread.currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(Thread.currentThread(), e);
            }
        } catch (Exception ex) {
            if (this.cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

    public static void main(String[] args) {
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.LifecycleAdapter<>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished done.");
            return "Hello Observer";
        }, lifecycle);
        observableThread.start();
    }
}
