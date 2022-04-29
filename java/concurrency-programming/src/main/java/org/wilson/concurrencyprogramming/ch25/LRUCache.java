package org.wilson.concurrencyprogramming.ch25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class LRUCache<K, V> {
    private final LinkedList<K> keyList = new LinkedList<>();
    private final Map<K, V> cache = new HashMap<>();
    private final CacheLoader<K, V> cacheLoader;
    private final int capacity;

    public LRUCache(CacheLoader<K, V> cacheLoader, int capacity) {
        this.cacheLoader = cacheLoader;
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (this.keyList.size() >= this.capacity) {
            K eldestKey = this.keyList.removeFirst();
            this.cache.remove(eldestKey);
        }
        this.keyList.remove(key);
        this.keyList.addLast(key);
        this.cache.put(key, value);
    }

    public V get(K key) {
        V value;
        boolean success = this.keyList.remove(key);
        if (!success) {
            value = this.cacheLoader.load(key);
            this.put(key, value);
        } else {
            value = this.cache.get(key);
            this.keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }

    public static void main(String[] args) {
        LRUCache<Integer, Reference> cache = new LRUCache<>(key -> new Reference(), 200);
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
