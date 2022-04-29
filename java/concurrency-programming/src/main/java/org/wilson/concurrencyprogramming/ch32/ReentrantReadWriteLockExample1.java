package org.wilson.concurrencyprogramming.ch32;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample1 {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = this.readWriteLock.readLock();
    private final Lock writeLock = this.readWriteLock.writeLock();
    private final LinkedList<String> list = new LinkedList<>();

    public void add(String element) {
        this.writeLock.lock();
        try {
            this.list.add(element);
        } finally {
            this.writeLock.unlock();
        }
    }

    public void take() {
        this.writeLock.lock();
        try {
            this.list.removeFirst();
        } finally {
            this.writeLock.unlock();
        }
    }

    public String get(int index) {
        this.readLock.lock();
        try {
            return this.list.get(index);
        } finally {
            this.readLock.unlock();
        }
    }
}
