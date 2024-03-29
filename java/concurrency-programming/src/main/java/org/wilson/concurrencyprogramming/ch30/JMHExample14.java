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
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *  Blackhole Usage
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample14 {
    double x1 = Math.PI;
    double x2 = Math.PI * 2;

    @Benchmark
    public double baseline() {
        return Math.pow(x1, 2);
    }

    @Benchmark
    public double putButReturnOne() {
        Math.pow(x1, 2);
        return Math.pow(x2, 2);
    }

    @Benchmark
    public double powThenAdd() {
        return Math.pow(x1, 2) + Math.pow(x2, 2);
    }

    @Benchmark
    public void useBlackhole(Blackhole blackhole) {
        blackhole.consume(Math.pow(x1, 2));
        blackhole.consume(Math.pow(x2, 2));
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample14.class.getSimpleName()).build()).run();
    }
}
