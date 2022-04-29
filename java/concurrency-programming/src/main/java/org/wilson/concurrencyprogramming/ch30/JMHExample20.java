package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Group)
public class JMHExample20 {
    @Param({"1", "2", "3", "4"})
    private int type;
    private Map<Integer, Integer> map;

    @Setup
    public void setup() {
        switch (this.type) {
            case 1 -> this.map = new ConcurrentHashMap<>();
            case 2 -> this.map = new ConcurrentSkipListMap<>();
            case 3 -> this.map = new Hashtable<>();
            case 4 -> this.map = Collections.synchronizedMap(new HashMap<>());
            default -> new IllegalArgumentException("Illegal map type.");
        }
    }

    @Benchmark
    @GroupThreads(5)
    @Group("g")
    public void putMap() {
        int random = this.randomIntValue();
        this.map.put(random, random);
    }

    @Benchmark
    @GroupThreads(5)
    @Group("g")
    public Integer getMap() {
        return this.map.get(this.randomIntValue());
    }

    private int randomIntValue() {
        return (int) Math.ceil(Math.random() * 600000);
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample20.class.getSimpleName()).build()).run();
    }
}
