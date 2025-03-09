package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

public class KeySetTest {
    /**
     * Хэш-маппа, которую будем тестировать
     */
    private MyHashMap<String, String> myHashMap;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Здесь инициализируется новый объект MyHashMap и добавляются тестовые
     * данные в виде пар "ключ-значение".
     */
    @Before
    public void setUp() {
        myHashMap = new MyHashMap<>();
        myHashMap.put("key1", "value1");
        myHashMap.put("key2", "value2");
        myHashMap.put("key3", "value3");
    }

    /**
     * Проверяет, что метод keySet возвращает все ключи, которые есть в хэш-маппе.
     */
    @Test
    public void testKeySetReturnsAllKeys() {
        Set<String> keys = myHashMap.keySet();
        assertTrue(keys.containsAll(Arrays.asList("key1", "key2", "key3")));
        assertEquals(3, keys.size());
    }

    /**
     * Проверяет, что метод возвращает правильные ключи после удаления ключа.
     */

    @Test
    public void testKeySetAfterRemove() {
        myHashMap.remove("key2");
        Set<String> keys = myHashMap.keySet();
        assertTrue(keys.contains("key1"));
        assertFalse(keys.contains("key2"));
        assertTrue(keys.contains("key3"));
        assertEquals(2, keys.size());
    }

    /**
     * Проверяет, что метод keySet возвращает пустой набор, когда хэш-маппа пуста.
     */

    @Test
    public void testKeySetWhenEmpty() {
        MyHashMap<String, String> emptyMap = new MyHashMap<>();
        Set<String> keys = emptyMap.keySet();
        assertTrue(keys.isEmpty());
    }

    /**
     * Проверяет, что ключ null корректно добавляется и возвращается в наборе ключей.
     */

    @Test
    public void testKeySetWithNullKey() {
        myHashMap.put(null, "nullValue");
        Set<String> keys = myHashMap.keySet();
        assertTrue(keys.contains(null));
        assertEquals(4, keys.size());
    }
}
