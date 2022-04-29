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
 *  Profile-guided optimization
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(0)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample17 {

    private final Inc inc1 = new Inc1();
    private final Inc inc2 = new Inc2();

    private int measure(Inc inc) {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += inc.inc();
        }
        return result;
    }

    @Benchmark
    public int measure_inc_1() {
        return this.measure(this.inc1);
    }

    @Benchmark
    public int measure_inc_2() {
        return this.measure(this.inc2);
    }

    @Benchmark
    public int measure_inc_3() {
        return this.measure(this.inc1);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample17.class.getSimpleName()).build()).run();
    }

    interface Inc {
        int inc();
    }

    public static class Inc1 implements Inc {
        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }

    public static class Inc2 implements Inc {
        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }
}
