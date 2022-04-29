package org.wilson.concurrencyprogramming.ch31;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(1)
@State(Scope.Thread)
public class LazySetVsSet {
    private AtomicInteger ai;

    @Setup(Level.Iteration)
    public void setup() {
        this.ai = new AtomicInteger(0);
    }

    @Benchmark
    public void testSet() {
        this.ai.set(10);
    }

    @Benchmark
    public void testLazySet() {
        this.ai.lazySet(10);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(LazySetVsSet.class.getSimpleName()).build()).run();
    }
}
