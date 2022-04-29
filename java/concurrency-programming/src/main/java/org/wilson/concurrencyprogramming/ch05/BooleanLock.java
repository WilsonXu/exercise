package org.wilson.concurrencyprogramming.ch05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (this.locked) {
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!this.blockedList.contains(tempThread)) {
                        this.blockedList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    this.blockedList.remove(tempThread);
                    throw e;
                }
            }
            this.blockedList.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMillis = System.currentTimeMillis() + remainingMills;
                final Thread tempThread = Thread.currentThread();
                while (this.locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during " + mills + " ms.");
                    }
                    try {
                        if (!this.blockedList.contains(tempThread)) {
                            this.blockedList.add(tempThread);
                        }
                        this.wait(remainingMills);
                    } catch (InterruptedException e) {
                        this.blockedList.remove(tempThread);
                        throw e;
                    }
                    remainingMills = endMillis - System.currentTimeMillis();
                }
                this.blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unblock() {
        synchronized (this) {
            if (this.currentThread == Thread.currentThread()) {
                this.locked = false;
                Optional.of(Thread.currentThread().getName() + " release the lock.").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(this.blockedList);
    }
}