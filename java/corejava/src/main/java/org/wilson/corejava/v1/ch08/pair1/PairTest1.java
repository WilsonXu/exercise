package org.wilson.corejava.v1.ch08.pair1;

import lombok.extern.slf4j.Slf4j;
import org.wilson.corejava.v1.ch08.data.Pair;

/**
 * Created by wilson on 2020/3/23.
 */
@Slf4j
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        var mm = ArrayAlg.minMax(words);
        if (mm == null) return;
        log.info("min = {}", mm.getFirst());
        log.info("max = {}", mm.getSecond());
    }
}

class ArrayAlg {
    private ArrayAlg() {
    }

    public static Pair<String> minMax(String[] a) {
        if (a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}
