package org.wilson.concurrencyprogramming.ch31;

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
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Timeout(time = 10)
@Measurement(iterations = 10, time = 1)
@Warmup(iterations = 10, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class AtomicReferenceExample4 {
    @State(Scope.Group)
    public static class MonitorRace {
        private DebitCard debitCard = new DebitCard("Wilson", 0);

        public void syncInc() {
            synchronized (AtomicReferenceExample4.class) {
                final var dc = this.debitCard;
                final var newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                this.debitCard = newDc;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicRefrenceRace {
        private AtomicReference<DebitCard> debitCardRef = new AtomicReference<>((new DebitCard("Wilson", 0)));

        public void casInc() {
            final var dc = this.debitCardRef.get();
            final var newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
            this.debitCardRef.compareAndSet(dc, newDc);
        }
    }

    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void syncInc(MonitorRace monitorRace) {
        monitorRace.syncInc();
    }

    @GroupThreads(10)
    @Group("cas")
    @Benchmark
    public void casInc(AtomicRefrenceRace atomicRefrenceRace) {
        atomicRefrenceRace.casInc();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(AtomicReferenceExample4.class.getSimpleName()).addProfiler(StackProfiler.class).build()).run();
    }
}
