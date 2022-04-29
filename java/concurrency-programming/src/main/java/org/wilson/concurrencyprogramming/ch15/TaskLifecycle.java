package org.wilson.concurrencyprogramming.ch15;

public interface TaskLifecycle<T> {
    void onStart (Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);

    class LifecycleAdapter<T> implements TaskLifecycle<T> {
        @Override
        public void onStart(Thread thread) {
        }

        @Override
        public void onRunning(Thread thread) {
        }

        @Override
        public void onFinish(Thread thread, T result) {
        }

        @Override
        public void onError(Thread thread, Exception e) {
        }
    }
}
