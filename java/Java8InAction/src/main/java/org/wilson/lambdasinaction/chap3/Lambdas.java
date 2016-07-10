package org.wilson.lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wilson on 7/9/16.
 */
public class Lambdas {
    public static void main(String ... args) {
        Runnable r = () -> System.out.println("Hello!");
        r.run();

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));

        List<Apple> greenApples = filter(inventory, p -> "green".equals(p.getColor()));
        System.out.println(greenApples);

        Comparator<Apple> comparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(comparator);
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple {" +
                    "color='" + this.color + '\'' +
                    ", weight=" + this.weight +
                    '}';
        }
    }

    @FunctionalInterface
    interface ApplePredicate {
        public boolean test(Apple apple);
    }
}
