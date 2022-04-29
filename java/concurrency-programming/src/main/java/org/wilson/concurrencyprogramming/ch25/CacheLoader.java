package org.wilson.concurrencyprogramming.ch25;

@FunctionalInterface
public interface CacheLoader<K, V> {
    V load(K k);
}
