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
public class ArrayListVsSkipListBinarySearch {
    private List<Integer> arrayList;
    private SimpleSkipList skipList;
    private Random random;

    @Setup(Level.Trial)
    public void setup() {
        this.random = new Random(System.currentTimeMillis());
        this.arrayList = new ArrayList<>();
        this.skipList = new SimpleSkipList();
        IntStream.range(0, 500000).forEach(i -> {
            ArrayListVsSkipListBinarySearch.this.arrayList.add(i);
            ArrayListVsSkipListBinarySearch.this.skipList.add(i);
        });
    }

    @Benchmark
    public void binarySearchFromArrayList(Blackhole blackhole) {
        blackhole.consume(Collections.binarySearch(this.arrayList, this.random.nextInt(500000)));
    }

    @Benchmark
    public void searchFromSkipList(Blackhole blackhole) {
        blackhole.consume(this.skipList.get(this.random.nextInt(500000)));
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(ArrayListVsSkipListBinarySearch.class.getSimpleName()).build()).run();
    }
}
