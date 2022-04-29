package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample01 {
    private final static String DATA = "DUMMY DATA";

    private List<String> arrayList;
    private List<String> linkedList;

    @Setup(Level.Iteration)
    public void setup() {
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
    }

    @Benchmark
    public List<String> arrayListAdd() {
        this.arrayList.add(JMHExample01.DATA);
        return this.arrayList;
    }

    @Benchmark
    public List<String> linkedListAdd() {
        this.linkedList.add(JMHExample01.DATA);
        return this.linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample01.class.getSimpleName())
                .forks(1).measurementIterations(10).warmupIterations(10).build();
        new Runner(opts).run();
    }
}
