package org.wilson.lambdasinaction.chap6;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

/**
 * Created by wilson on 7/11/16.
 */
public class Summarizing {
    public static void main(String... args) {
        System.out.println("Nr. of dishes: " + Summarizing.howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }

    private static Long howManyDishes() {
        return Dish.menu.stream().collect(Collectors.counting());
    }

    private static Dish findMostCaloricDish() {
        return Dish.menu.stream().collect(Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        return Dish.menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))).get();
    }

    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    }

    private static Double calculateAverageCalories() {
        return Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
    }

    private static String getShortMenuCommaSeparated() {
        return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }
}
