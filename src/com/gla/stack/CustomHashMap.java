package com.gla.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class CustomHashMap<K, V> {
    private final LinkedList<Entry<K, V>>[] buckets;

    private static class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int bucketCount) {
        if (bucketCount < 1) {
            throw new IllegalArgumentException("Bucket count must be positive");
        }

        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[bucketCount];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        LinkedList<Entry<K, V>> bucket = bucketFor(key);
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        for (Entry<K, V> entry : bucketFor(key)) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        Iterator<Entry<K, V>> entries = bucketFor(key).iterator();
        while (entries.hasNext()) {
            Entry<K, V> entry = entries.next();
            if (Objects.equals(entry.key, key)) {
                entries.remove();
                return entry.value;
            }
        }
        return null;
    }

    private LinkedList<Entry<K, V>> bucketFor(K key) {
        return buckets[Math.floorMod(Objects.hashCode(key), buckets.length)];
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> marks = new CustomHashMap<>(4);
        marks.put("Aa", 80);
        marks.put("BB", 92); // "Aa" and "BB" have the same String hash.
        marks.put("Aa", 85);
        System.out.println(marks.get("Aa"));
        System.out.println(marks.remove("BB"));
        System.out.println(marks.get("BB"));
    }
}
