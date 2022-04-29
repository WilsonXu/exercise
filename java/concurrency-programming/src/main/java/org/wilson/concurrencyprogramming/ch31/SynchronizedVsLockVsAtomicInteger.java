package org.wilson.concurrencyprogramming.ch31;

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
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(1)
@Timeout(time = 10)
public class SynchronizedVsLockVsAtomicInteger {
    @State(Scope.Group)
    public static class IntMonitor {
        private int x;
        private final Lock lock = new ReentrantLock();

        public void lockInc() {
            this.lock.lock();
            try {
                this.x ++;
            } finally {
                this.lock.unlock();
            }
        }

        public void synInc() {
            synchronized (this) {
                this.x ++;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicIntegerMonitor {
        private AtomicInteger x = new AtomicInteger();

        public void inc() {
            this.x.incrementAndGet();
        }
    }

    @Benchmark
    @GroupThreads(10)
    @Group("sync")
    public void syncInc(IntMonitor intMonitor) {
        intMonitor.synInc();
    }

    @Benchmark
    @GroupThreads(10)
    @Group("lock")
    public void lockInc(IntMonitor intMonitor) {
        intMonitor.lockInc();
    }

    @Benchmark
    @GroupThreads(10)
    @Group("atomic")
    public void atomicIntegerInc(AtomicIntegerMonitor atomicIntegerMonitor) {
        atomicIntegerMonitor.inc();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(SynchronizedVsLockVsAtomicInteger.class.getSimpleName()) .addProfiler(StackProfiler.class).build()).run();
    }
}
