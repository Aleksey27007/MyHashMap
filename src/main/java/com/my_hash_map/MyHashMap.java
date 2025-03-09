package com.my_hash_map;

import java.util.*;

public class MyHashMap<K, V> implements MyMapInterface<K, V>{
    private final int DEFAULT_LENGTH = 16;
    private LinkedList<Entry<K, V>>[] bucket;
    private int size;


    private static class Entry<K,V> {
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public MyHashMap() {
        this.bucket = new LinkedList[DEFAULT_LENGTH];
        this.size = 0;
    }
    
    private int hash(K key) {
        return Objects.hashCode(key) % bucket.length;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (bucket[index] != null) {
            for (Entry<K, V> entry : bucket[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hash(key);
        if(bucket[index] == null) {
            bucket[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry: bucket[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                break;
            }
        }
        bucket[index].add(new Entry<>(key, value));
        size++;
        return true;
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        if (bucket[index] != null) {
            Entry<K, V> toRemove = null;
            for (Entry<K, V> entry : bucket[index]) {
                if (entry.key.equals(key)) {
                    toRemove = entry;
                    break;
                }
            }
            if (toRemove != null) {
                bucket[index].remove(toRemove);
                size--;
            }
        }
        return true;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    public int size() {
        return size;
    }
}
