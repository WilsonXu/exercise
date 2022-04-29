package org.wilson.concurrencyprogramming.ch32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockExample2.class);
    private static final Lock LOCK1 = new ReentrantLock();
    private static final Lock LOCK2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                ReentrantLockExample2.m1();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                ReentrantLockExample2.m2();
            }
        }).start();
    }

    private static void m1() {
        ReentrantLockExample2.LOCK1.lock();
        if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
            ReentrantLockExample2.LOGGER.info("{} got lock1.", Thread.currentThread());
        }
        try {
            ReentrantLockExample2.LOCK2.lock();
            if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                ReentrantLockExample2.LOGGER.info("{} got lock2.", Thread.currentThread());
            }
            try {
                //...
            } finally {
                ReentrantLockExample2.LOCK2.unlock();
                if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                    ReentrantLockExample2.LOGGER.info("{} released lock2.", Thread.currentThread());
                }
            }
        } finally {
            ReentrantLockExample2.LOCK1.unlock();
            if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                ReentrantLockExample2.LOGGER.info("{} released lock1.", Thread.currentThread());
            }
        }
    }

    private static void m2() {
        ReentrantLockExample2.LOCK2.lock();
        if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
            ReentrantLockExample2.LOGGER.info("{} got lock2.", Thread.currentThread());
        }
        try {
            ReentrantLockExample2.LOCK1.lock();
            if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                ReentrantLockExample2.LOGGER.info("{} got lock1.", Thread.currentThread());
            }
            try {
                //...
            } finally {
                ReentrantLockExample2.LOCK1.unlock();
                if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                    ReentrantLockExample2.LOGGER.info("{} released lock1.", Thread.currentThread());
                }
            }
        } finally {
            ReentrantLockExample2.LOCK2.unlock();
            if (ReentrantLockExample2.LOGGER.isInfoEnabled()) {
                ReentrantLockExample2.LOGGER.info("{} released lock2.", Thread.currentThread());
            }
        }
    }
}
