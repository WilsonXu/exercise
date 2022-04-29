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
import java.util.concurrent.locks.StampedLock;

@BenchmarkMode(Mode.Throughput)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
public class StampedLockExample4 {
    @State(Scope.Group)
    public static class Test {
        private final Lock lock = new ReentrantLock();
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = this.readWriteLock.readLock();
        private final Lock writeLock = this.readWriteLock.writeLock();
        private final StampedLock stampedLock = new StampedLock();

        private int x = 10;

        public void stampedLockInc() {
            var stamp = this.stampedLock.writeLock();
            try {
                this.x ++;
            } finally {
                this.stampedLock.unlock(stamp);
            }
        }

        public int stampedLockGet() {
            var stamp = this.stampedLock.readLock();
            try {
                return this.x;
            } finally {
                this.stampedLock.unlock(stamp);
            }
        }

        public int stampedOptimisticLockGet() {
            var stamp = this.stampedLock.tryOptimisticRead();
            if (!this.stampedLock.validate(stamp)) {
                stamp = this.stampedLock.readLock();
                try {
                    return this.x;
                } finally {
                    this.stampedLock.unlock(stamp);
                }
            }
            return this.x;
        }

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
    @Group("stampedLock")
    public void writeStampedLockInc(Test test) {
        test.stampedLockInc();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("stampedLock")
    public void readStampedLockGet(Blackhole hole, Test test) {
        hole.consume(test.stampedLockGet());
    }

    @Benchmark
    @GroupThreads(5)
    @Group("stampedLockOptimistic")
    public void writeStampedLockInc2(Test test) {
        test.stampedLockInc();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("stampedLockOptimistic")
    public void readStampedLockGet2(Blackhole hole, Test test) {
        hole.consume(test.stampedOptimisticLockGet());
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(StampedLockExample4.class.getSimpleName()).build()).run();
    }
}
