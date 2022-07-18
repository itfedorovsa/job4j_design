package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        V val = value;
        SoftReference<V> soft = new SoftReference<>(val);
        cache.putIfAbsent(key, soft);
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            put(key, load(key));
        }
        SoftReference<V> soft = cache.get(key);
        V rsl = soft.get();
        return rsl;
    }

    protected abstract V load(K key);
}
