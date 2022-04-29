package org.wilson.concurrencyprogramming.ch32;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
public class ReentrantReadWriteLockExample6 {
    @State(Scope.Group)
    public static class Test {
        private final Lock lock = new ReentrantLock();
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = this.readWriteLock.readLock();
        private final Lock writeLock = this.readWriteLock.writeLock();

        private int x = 10;

        public void lockInc() {
            this.lock.lock();
            try {
                this.x ++;
            } finally {
                this.lock.unlock();
            }
        }

        public int lockGet() {
            this.lock.lock();
            try {
                return this.x;
            } finally {
                this.lock.unlock();
            }
        }

        public void writeLockInc() {
            this.writeLock.lock();
            try {
                this.x ++;
            } finally {
                this.writeLock.unlock();
            }
        }

        public int readLockGet() {
            this.readLock.lock();
            try {
                return this.x;
            } finally {
                this.readLock.unlock();
            }
        }

        public void syncInc() {
            synchronized (this) {
                this.x ++;
            }
        }

        public int syncGet() {
            synchronized (this) {
                return this.x;
            }
        }
    }

    @Benchmark
    @GroupThreads(5)
    @Group("lock")
    public void lockInc(Test test) {
        test.lockInc();
    }


    @Benchmark
    @GroupThreads(5)
    @Group("lock")
    public void lockGet(Blackhole hole, Test test) {
        hole.consume(test.lockGet());
    }

    @Benchmark
    @GroupThreads(5)
    @Group("rwlock")
    public void writeLockInc(Test test) {
        test.writeLockInc();
    }


    @Benchmark
    @GroupThreads(5)
    @Group("rwlock")
    public void readLockGet(Blackhole hole, Test test) {
        hole.consume(test.readLockGet());
    }

    @Benchmark
    @GroupThreads(5)
    @Group("sync")
    public void syncInc(Test test) {
        test.syncInc();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("sync")
    public void syncGet(Blackhole hole, Test test) {
        hole.consume(test.syncGet());
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(ReentrantReadWriteLockExample6.class.getSimpleName()).build()).run();
    }
}
