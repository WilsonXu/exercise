package org.wilson.lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by wilson on 7/10/16.
 */
public class StreamVsCollection {
    public static void main (String... args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
        // uncommenting this line will result in an IllegalStateException
        // because streams can be consumed only once
        // stream.forEach(System.out::println);
    }
}
