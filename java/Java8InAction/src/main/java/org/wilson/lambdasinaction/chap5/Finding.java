package org.wilson.lambdasinaction.chap5;

import org.wilson.lambdasinaction.chap4.Dish;

import java.util.Optional;

/**
 * Created by wilson on 7/10/16.
 */
public class Finding {
    public static void main(String... args) {
        if (isVegetarianFrientlyMenu()) {
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    public static boolean isVegetarianFrientlyMenu() {
        return Dish.menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu(){
        return Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2() {
        return Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish(){
        return Dish.menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
