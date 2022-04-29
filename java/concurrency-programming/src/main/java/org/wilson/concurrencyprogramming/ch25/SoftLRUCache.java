package org.wilson.concurrencyprogramming.ch25;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SoftLRUCache<K, V> {
    private final LinkedList<K> keyList = new LinkedList<>();
    private final Map<K, SoftReference<V>> cache = new HashMap<>();
    private final CacheLoader<K, V> cacheLoader;
    private final int capacity;

    public SoftLRUCache(CacheLoader<K, V> cacheLoader, int capacity) {
        this.cacheLoader = cacheLoader;
        this.capacity = capacity;
    }

    @SuppressWarnings("DuplicatedCode")
    public void put(K key, V value) {
        if (this.keyList.size() >= this.capacity) {
            K eldestKey = this.keyList.removeFirst();
            this.cache.remove(eldestKey);
        }
        this.keyList.remove(key);
        this.keyList.addLast(key);
        this.cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V value;
        boolean success = this.keyList.remove(key);
        if (!success) {
            value = this.cacheLoader.load(key);
            this.put(key, value);
        } else {
            value = this.cache.get(key).get();
            this.keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        SoftLRUCache<Integer, Reference> cache = new SoftLRUCache<>(key -> new Reference(), 1000);
        IntStream.range(0, Integer.MAX_VALUE).forEach(i -> {
            cache.get(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The " + i + " reference is stored at cache.");
        });
    }
}
