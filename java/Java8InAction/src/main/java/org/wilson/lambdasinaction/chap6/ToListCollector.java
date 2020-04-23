package org.wilson.lambdasinaction.chap6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by wilson on 7/11/16.
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>>  {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList<T>::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List<T>::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (List<T> l1, List<T> l2) -> {l1.addAll(l2); return l1;};
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
