package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
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

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class JMHExample04 {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void testAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void testSampleTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.SingleShotTime)
    public void testSingleShotTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void testThroughputAndAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void testAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample04.class.getSimpleName()).forks(1).build()).run();
    }
}
