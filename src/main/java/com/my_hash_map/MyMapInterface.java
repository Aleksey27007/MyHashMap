package com.my_hash_map;

import java.util.Collection;
import java.util.Set;
import com.my_hash_map.MyHashMap.Entry;

public interface MyMapInterface<K, V> {
    /**
     * Основные методы для работы с маппой
     */
    V get(K key);

    boolean put(K key, V value);

    boolean remove(K key);

    Collection<V> values();

    Set<K> keySet();

    Set<Entry<K, V>> entrySet();
}
