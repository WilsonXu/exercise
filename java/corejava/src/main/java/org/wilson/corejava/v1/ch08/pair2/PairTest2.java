package org.wilson.corejava.v1.ch08.pair2;

import lombok.extern.slf4j.Slf4j;
import org.wilson.corejava.v1.ch08.data.Pair;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by wilson on 2020/3/23.
 */
@Slf4j
public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22)
        };

        var mm = ArrayAlg.minMax(birthdays);
        if (mm == null) return;
        log.info("min = {}", mm.getFirst());
        log.info("max = {}", mm.getSecond());
    }
}

class ArrayAlg {
    private ArrayAlg() {
    }

    public static <T extends Comparable> Pair<T> minMax(T[] a) {
        if (a == null || a.length == 0) return null;
        var pair = new Pair<>(a[0], a[0]);
        Arrays.stream(a, 1, a.length).forEach(s -> {
            if (pair.getFirst().compareTo(s) > 0) pair.setFirst(s);
            if (pair.getSecond().compareTo(s) < 0) pair.setSecond(s);
        });
        return pair;
    }
}
