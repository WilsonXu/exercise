package org.wilson.lambdasinaction.chap2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wilson on 7/7/16.
 */
public class FilteringApples {
    static Logger logger = LoggerFactory.getLogger(FilteringApples.class);

    public static void main(String ... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN),
                                              new Apple(155, Color.GREEN),
                                              new Apple(120, Color.RED));
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        if (logger.isInfoEnabled()) {
            logger.info(greenApples.toString());
        }

        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        if (logger.isInfoEnabled()) {
            logger.info(redApples.toString());
        }

        List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
        if (logger.isInfoEnabled()) {
            logger.info(greenApples2.toString());
        }

        List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
        if (logger.isInfoEnabled()) {
            logger.info(heavyApples.toString());
        }

        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        if (logger.isInfoEnabled()) {
            logger.info(redAndHeavyApples.toString());
        }

        List<Apple> redApples2 = filter(inventory, apple -> apple.getColor().equals(Color.RED));
        if (logger.isInfoEnabled()) {
            logger.info(redApples2.toString());
        }
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return  result;
    }

    public static class Apple {
        private int weight = 0;
        private Color color = null;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
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

    public enum Color {
        GREEN, RED
    }

    interface  ApplePredicate {
        public boolean test(Apple apple);
    }

    static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return Color.GREEN.equals(apple.getColor());
        }
    }

    static  class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return  Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }
}
