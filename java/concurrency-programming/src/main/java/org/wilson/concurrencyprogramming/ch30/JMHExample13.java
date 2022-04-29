package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *  Dead Code Elimination
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample13 {
    @Benchmark
    public void baseline() {
    }

    @Benchmark
    public void measureLog1() {
        Math.log(Math.PI);
    }

    @Benchmark
    public void measureLog2() {
        double result = Math.log(Math.PI);
        Math.log(result);
    }

    @Benchmark
    public double measureLog3() {
        return Math.log(Math.PI);
    }


    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample13.class.getSimpleName()).build()).run();
    }
}
