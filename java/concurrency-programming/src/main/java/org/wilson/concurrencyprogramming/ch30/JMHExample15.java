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
 *  Constant Folding
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample15 {

    private final double x1 = 124.456;
    private final double x2 = 342.456;

    private double y1 = 124.456;
    private double y2 = 342.456;

    @Benchmark
    public double returnDirect() {
        return 42620.703936d;
    }

    @Benchmark
    public double returnCaculate_1() {
        return x1 * x2;
    }

    @Benchmark
    public double returnCaculate_2() {
        return y1 * y2;
    }

    @Benchmark
    public double returnCaculate_3() {
        return Math.log(x1) * Math.log(x2);
    }

    @Benchmark
    public double returnCaculate_4() {
        return Math.log(y1) * Math.log(y2);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample15.class.getSimpleName()).build()).run();
    }
}
