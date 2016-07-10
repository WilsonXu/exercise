package org.wilson.lambdasinaction.chap5;

import org.wilson.lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wilson on 7/10/16.
 */
public class Mapping {
    public static void main(String... args) {
        List<String> dishNames =
                Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        dishNames.forEach(System.out::println);

        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths =
                words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);

        words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                numbers1.stream()
                .flatMap(i -> numbers2.stream()
                    .map(j -> new int[] {i, j})
                )
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(Collectors.toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
