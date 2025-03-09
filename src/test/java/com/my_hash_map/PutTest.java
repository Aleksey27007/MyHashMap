package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PutTest {
    /**
     * Хэш-маппа, которую будем тестировать
     */
    private MyHashMap<String, String> myHashMap;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Здесь инициализируется новый объект MyHashMap.
     */
    @Before
    public void setUp() {
        myHashMap = new MyHashMap<>();
    }

    /**
     * Проверяет добавление новой пары "ключ-значение".
     */
    @Test
    public void testPutNewKey() {
        boolean result = myHashMap.put("key1", "value1");
        assertTrue(result);
        assertEquals("value1", myHashMap.get("key1"));
    }
    /**
     * Проверяет добавление значения с ключом равным null.
     */
    @Test
    public void testPutNullKey() {
        boolean result = myHashMap.put(null, "valueNull");
        assertTrue(result);
        assertEquals("valueNull", myHashMap.get(null));
    }

    /**
     * Проверяет, что обновление значения для существующего ключа работает правильно.
     */
    @Test
    public void testPutExistingKey() {
        myHashMap.put("key1", "value1");
        boolean result = myHashMap.put("key1", "newValue");
        assertTrue(result);
        assertEquals("newValue", myHashMap.get("key1"));
    }

    /**
     * Проверяет, что метод getSize() показывает правильное количество элементов после добавления.
     */
    @Test
    public void testSizeIncrease() {
        myHashMap.put("key1", "value1");
        myHashMap.put("key2", "value2");
        assertEquals(2, myHashMap.getSize());
    }

    /**
     * Проверяет добавление нескольких ключей, чтобы удостовериться, что они были добавлены в соответствующие корзины.
     */
    @Test
    public void testPutMultipleKeysDifferentBuckets() {
        myHashMap.put("key1", "value1");
        myHashMap.put("key2", "value2");
        myHashMap.put("key3", "value3");
        assertEquals("value1", myHashMap.get("key1"));
        assertEquals("value2", myHashMap.get("key2"));
        assertEquals("value3", myHashMap.get("key3"));
    }
}
