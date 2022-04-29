package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *  Loop Unwinding
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample16 {

    private int x = 1;
    private int y = 2;

    @Benchmark
    public double measure() {
        return (x + y);
    }

    private double loopCompute(int times) {
        int result = 0;
        for (int i = 0; i < times; i++) {
            result += (x + y);
        }
        return result;
    }

    @OperationsPerInvocation
    @Benchmark
    public double measureLoop_1() {
        return loopCompute(1);
    }

    @OperationsPerInvocation(10)
    @Benchmark
    public double measureLoop_10() {
        return loopCompute(10);
    }

    @OperationsPerInvocation(100)
    @Benchmark
    public double measureLoop_100() {
        return loopCompute(100);
    }

    @OperationsPerInvocation(1000)
    @Benchmark
    public double measureLoop_1000() {
        return loopCompute(1000);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample16.class.getSimpleName()).build()).run();
    }
}
