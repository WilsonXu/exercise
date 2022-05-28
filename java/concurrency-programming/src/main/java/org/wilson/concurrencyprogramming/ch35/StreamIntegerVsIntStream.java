package org.wilson.concurrencyprogramming.ch35;

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
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class StreamIntegerVsIntStream {
    private Stream<Integer> integerStream;
    private IntStream intStream;

    @Setup(Level.Invocation)
    public void init() {
        this.integerStream = IntStream.range(0, 100).boxed();
        this.intStream = IntStream.range(0, 100);
    }

    @SuppressWarnings("java:S3655")
    @Benchmark
    public void streamIntegerRedue(Blackhole hole) {
        hole.consume(this.integerStream.map(i -> i * 10).reduce((a, b) -> a+b).get());
    }

    @Benchmark
    public void streamIntegerUnboxRedue(Blackhole hole) {
        hole.consume(this.integerStream.mapToInt(Integer::intValue).map(i -> i * 10).reduce((a, b) -> a+b).getAsInt());
    }

    @Benchmark
    public void intStreamRedue(Blackhole hole) {
        hole.consume(this.intStream.map(i -> i * 10).reduce((a, b) -> a+b).getAsInt());
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(StreamIntegerVsIntStream.class.getSimpleName()).build()).run();
    }
}
