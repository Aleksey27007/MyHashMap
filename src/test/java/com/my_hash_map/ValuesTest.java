package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class ValuesTest {
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
     * Проверяет, что метод values возвращает все значения в хэш-маппе.
     */
    @Test
    public void testValuesReturnsAllValues() {
        Collection<String> values = myHashMap.values();
        assertTrue(values.containsAll(Arrays.asList("value1", "value2", "value3")));
        assertEquals(3, values.size());
    }

    /**
     * Проверяет возврат значений после удаления ключа.
     */
    @Test
    public void testValuesAfterRemove() {
        myHashMap.remove("key2");
        Collection<String> values = myHashMap.values();
        assertTrue(values.contains("value1"));
        assertFalse(values.contains("value2"));
        assertTrue(values.contains("value3"));
        assertEquals(2, values.size());
    }

    /**
     * Проверяет, что метод values возвращает пустую коллекцию, когда хэш-маппа пуста.
     */
    @Test
    public void testValuesWhenEmpty() {
        MyHashMap<String, String> emptyMap = new MyHashMap<>();
        Collection<String> values = emptyMap.values();
        assertTrue(values.isEmpty());
    }

    /**
     * Проверяет, что значения с ключом null также правильно добавляются.
     */
    @Test
    public void testValuesWithNullValue() {
        myHashMap.put(null, "nullValue");
        Collection<String> values = myHashMap.values();
        assertTrue(values.contains("nullValue"));
        assertEquals(4, values.size());
    }
}
