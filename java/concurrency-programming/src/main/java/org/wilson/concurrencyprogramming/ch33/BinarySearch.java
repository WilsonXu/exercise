package org.wilson.concurrencyprogramming.ch33;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
@State(Scope.Thread)
public class BinarySearch {
    private List<Integer> arrayList;
    private List<Integer> linkedList;
    private Random random;

    @Setup(Level.Trial)
    public void setup() {
        this.random = new Random(System.currentTimeMillis());
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
        IntStream.range(0, 10000000).forEach(i -> {
            BinarySearch.this.arrayList.add(i);
            BinarySearch.this.linkedList.add(i);
        });
    }

    @Benchmark
    public void binarySearchFromArrayList(Blackhole blackhole) {
        blackhole.consume(Collections.binarySearch(this.arrayList, this.random.nextInt(10000000)));
    }

    @Benchmark
    public void binarySearchFromLinkedList(Blackhole blackhole) {
        blackhole.consume(Collections.binarySearch(this.linkedList, this.random.nextInt(10000000)));
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(BinarySearch.class.getSimpleName()).build()).run();
    }
}
