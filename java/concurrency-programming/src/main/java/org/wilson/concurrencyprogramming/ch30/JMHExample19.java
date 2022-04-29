package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
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
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  Interrupts Benchmark
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Group)
public class JMHExample19 {
    private final static int VALUE = Integer.MAX_VALUE;

    private BlockingQueue<Integer> queue;

    @Setup
    public void setup() {
        this.queue = new ArrayBlockingQueue<>(10);
    }

    @Group("blockingQueue")
    @GroupThreads(5)
    @Benchmark
    public void put() throws InterruptedException {
        this.queue.put(JMHExample19.VALUE);
    }

    @Group("blockingQueue")
    @GroupThreads(5)
    @Benchmark
    public int take() throws InterruptedException {
        return this.queue.take();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample19.class.getSimpleName()).timeout(TimeValue.seconds(10)).build()).run();
    }
}
