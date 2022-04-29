package org.wilson.concurrencyprogramming.ch30;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Thread)
public class JMHExample22 {
    private byte[] wilsonBytes;
    private MyClassLoader classLoader;

    @Setup
    public void init() throws URISyntaxException, IOException {
        this.wilsonBytes = Files.readAllBytes(Path.of(JMHExample22.class.getResource("Wilson.class").toURI()));
        this.classLoader = new MyClassLoader(this.wilsonBytes);
    }

    @Benchmark
    public Object testLoadClass() throws Exception {
        Class<?> wilsonClass = Class.forName("org.wilson.concurrencyprogramming.ch30.Wilson", true, this.classLoader);
        return wilsonClass.getDeclaredConstructor().newInstance();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExample22.class.getSimpleName()).addProfiler(GCProfiler.class).jvmArgsAppend("-Xmx128m").build()).run();
    }

}
