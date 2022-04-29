package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample02 {
    @SuppressWarnings("unused")
    public void normalMethod() {
    }

    public static void main(String[] args) throws RunnerException {
        var options = new OptionsBuilder().include(JMHExample02.class.getSimpleName()).
                forks(1).measurementIterations(10).warmupIterations(10).build();
        new Runner(options).run();
    }
}
