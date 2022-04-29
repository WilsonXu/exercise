package org.wilson.concurrencyprogramming.ch30;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ArrayListVSLinkedList {
    private final static String DATA = "DUMMY DATA";
    private final static int MAX_CAPACITY = 1000000;
    private final static int MAX_ITERATIONS = 10;

    private static void test(List<String> list) {
        IntStream.range(0, ArrayListVSLinkedList.MAX_CAPACITY).forEach(i ->list.add(ArrayListVSLinkedList.DATA));
    }

    private static void arrayListPerfTest(int iterations) {
        IntStream.range(0, iterations).forEach(i -> {
            final List<String> list = new ArrayList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            ArrayListVSLinkedList.test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        });
    }

    private static void linkedListPerfTest(int iterations) {
        IntStream.range(0, iterations).forEach(i -> {
            final List<String> list = new LinkedList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            ArrayListVSLinkedList.test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        });
    }

    public static void main(String[] args) {
        ArrayListVSLinkedList.arrayListPerfTest(ArrayListVSLinkedList.MAX_ITERATIONS);
        System.out.println(Strings.repeat("#", 100));
        ArrayListVSLinkedList.linkedListPerfTest(ArrayListVSLinkedList.MAX_ITERATIONS);
    }
}
