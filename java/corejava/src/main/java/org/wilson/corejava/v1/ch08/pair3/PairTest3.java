package org.wilson.corejava.v1.ch08.pair3;

import lombok.extern.slf4j.Slf4j;
import org.wilson.corejava.v1.ch08.data.Employee;
import org.wilson.corejava.v1.ch08.data.Manager;
import org.wilson.corejava.v1.ch08.data.Pair;

import java.util.Arrays;

/**
 * Created by wilson on 2020/3/24.
 */
@Slf4j
public class PairTest3 {
    public static void main(String[] args) {
        var ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        var cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        var buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = {ceo, cfo};

        var result = new Pair<Employee>();
        minmaxBonus(managers, result);
        log.info("first: {}, second: {}", result.getFirst().getName(), result.getSecond().getName());
        maxminBonus(managers, result);
        log.info("first: {}, second: {}", result.getFirst().getName(), result.getSecond().getName());
    }

    public static void printBuddies(Pair<? extends Employee> p) {
        var first = p.getFirst();
        var second = p.getSecond();
        log.info("{} and {} are buddies.", first.getName(), second.getName());
    }

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) return;
        result.setFirst(a[0]);
        result.setSecond(a[0]);
        Arrays.stream(a, 1, a.length).forEach(m -> {
            if (((Manager) result.getFirst()).getBonus() > m.getBonus()) result.setFirst(m);
            if (((Manager) result.getSecond()).getBonus() < m.getBonus()) result.setSecond(m);
        });
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        PairAlg.swap(result);
    }
}

class PairAlg {
    private PairAlg() {
    }

    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) {
        swapHelp(p);
    }

    public static <T> void swapHelp(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}
