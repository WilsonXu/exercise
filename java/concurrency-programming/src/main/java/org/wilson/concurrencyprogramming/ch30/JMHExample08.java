package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
public class JMHExample08 {
    @State(Scope.Benchmark)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void write() {
            System.out.println("write");
        }

        public void read() {
            System.out.println("read");
        }
    }

    @Benchmark
    @GroupThreads(3)
    @Group("test")
    public void testWrite(Test test) {
        test.write();
    }

    @Benchmark
    @GroupThreads(3)
    @Group("test")
    public void testRead(Test test) {
        test.read();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample08.class.getSimpleName()).build()).run();
    }
}
