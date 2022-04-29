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
import java.util.concurrent.locks.ReentrantLock;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
public class ReentrantLockExample5 {
    @State(Scope.Group)
    public static class Test {
        @SuppressWarnings("FieldMayBeFinal")
        private int x = 10;
        private final Lock lock = new ReentrantLock();

        public int baseMethod() {
            return this.x;
        }

        public int lockMethod() {
            this.lock.lock();
            try {
                return x;
            } finally {
                this.lock.unlock();
            }
        }

        public int syncMethod() {
            synchronized (this) {
                return x;
            }
        }
    }

    @Benchmark
    @GroupThreads(10)
    @Group("base")
    public void base(Blackhole hole, Test test) {
        hole.consume(test.baseMethod());
    }

    @Benchmark
    @GroupThreads(10)
    @Group("lock")
    public void testLockMethod(Blackhole hole, Test test) {
        hole.consume(test.lockMethod());
    }

    @Benchmark
    @GroupThreads(10)
    @Group("sync")
    public void testSyncMethod(Blackhole hole, Test test) {
        hole.consume(test.syncMethod());
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(ReentrantLockExample5.class.getSimpleName()).build()).run();
    }
}
