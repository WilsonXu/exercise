package org.wilson.lambdasinaction.chap5;

import org.wilson.lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wilson on 7/10/16.
 */
public class Filtering {
    public static void main(String... args) {
        List<Dish> vegetarianMenu =
                Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        vegetarianMenu.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> dishesLimit3 =
                Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        dishesLimit3.forEach(System.out::println);

        List<Dish> dishesSkip2 =
                Dish.menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(Collectors.toList());
        dishesSkip2.forEach(System.out::println);
    }
}
