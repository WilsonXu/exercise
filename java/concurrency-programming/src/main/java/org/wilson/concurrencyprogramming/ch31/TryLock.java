package org.wilson.concurrencyprogramming.ch31;

import java.util.concurrent.atomic.AtomicBoolean;

public class TryLock {
    private final AtomicBoolean ab = new AtomicBoolean();
    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    public boolean tryLock() {
        boolean result = this.ab.compareAndSet(false, true);
        if (result) {
            this.threadLocal.set(true);
        }
        return result;
    }

    public boolean release() {
        if (this.threadLocal.get()) {
            this.threadLocal.set(false);
            return this.ab.compareAndSet(true, false);
        } else {
            return false;
        }
    }
}
