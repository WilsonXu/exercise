package org.wilson.corejava.v1.ch05.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by wilson on 2020/3/23.
 */
@Slf4j
public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String name;
        if (args.length > 0) name = args[0];
        else {
            var in = new Scanner(System.in);
            log.info("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        var cl = Class.forName(name);
        var suppercl = cl.getSuperclass();
        var modifiers = Modifier.toString(cl.getModifiers());
        var stringBuilder = new StringBuilder();
        if (modifiers.length() > 0) {
            stringBuilder.append(modifiers);
            stringBuilder.append(" ");
        }
        stringBuilder.append("class ");
        stringBuilder.append(name);
        if (suppercl != null && suppercl != Object.class) {
            stringBuilder.append(" extends ");
            stringBuilder.append(suppercl.getName());
        }

        if (log.isInfoEnabled()) {
            log.info(stringBuilder.toString());
            log.info("{}", "{");

            printConstructors(cl);
            log.info("");
            printMethods(cl);
            log.info("");
            printFields(cl);
            log.info("}");
        }
    }

    public static void printConstructors (Class cl) {
        Arrays.stream(cl.getDeclaredConstructors()).map(c -> {
            var stringBuilder = new StringBuilder("   ");
            var modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                stringBuilder.append(modifiers);
                stringBuilder.append(" ");
            }
            stringBuilder.append(c.getName());
            stringBuilder.append("(");
            stringBuilder.append(
                    Arrays.stream(c.getParameterTypes()).map(Class::getName).collect(Collectors.joining(",")));
            stringBuilder.append(")");
            return stringBuilder.toString();
        }).forEach(log::info);
    }

    public static void printMethods (Class cl) {
        Arrays.stream(cl.getDeclaredMethods()).map(m -> {
            var stringBuilder = new StringBuilder("   ");
            var modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                stringBuilder.append(modifiers);
                stringBuilder.append(" ");
            }
            stringBuilder.append(m.getReturnType().getName());
            stringBuilder.append(" ");
            stringBuilder.append(m.getName());
            stringBuilder.append("(");
            stringBuilder.append(
                    Arrays.stream(m.getParameterTypes()).map(Class::getName).collect(Collectors.joining(",")));
            stringBuilder.append(")");
            return stringBuilder.toString();
        }).forEach(log::info);
    }

    public static void printFields(Class cl) {
        Arrays.stream(cl.getDeclaredFields()).map(f -> {
            var stringBuilder = new StringBuilder("   ");
            var modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                stringBuilder.append(modifiers);
                stringBuilder.append(" ");
            }
            stringBuilder.append(f.getType().getName());
            stringBuilder.append(" ");
            stringBuilder.append(f.getName());
            stringBuilder.append(";");
            return stringBuilder.toString();
        }).forEach(log::info);
    }
}