package com.my_hash_map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyMapInterface<K, V> {
    /**
     * Основные методы для работы с маппой
     */
    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    Collection<V> values();

    Set<K> keySet();

    Set<Map.Entry<K, V>> entrySet();
}
