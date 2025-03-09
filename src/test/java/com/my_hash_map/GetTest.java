package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetTest {
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
     * Тест для проверки получения значения по существующему ключу.
     */

    @Test
    public void testGetExistingKey() {
        String result = myHashMap.get("key1");
        assertEquals("value1", result);
    }

    /**
     * Тест для проверки получения значения по другому существующему ключу.
     */

    @Test
    public void testGetAnotherExistingKey() {
        String result = myHashMap.get("key2");
        assertEquals("value2", result);
    }

    /**
     * Тест для проверки поведения метода get() после удаления ключа из хэш-таблицы.
     */

    @Test
    public void testGetAfterRemove() {
        myHashMap.remove("key3");
        String result = myHashMap.get("key3");
        assertNull(result);
    }

    /**
     * Тест для проверки работоспособности метода get() для ключа, равного null.
     */

    @Test
    public void testGetNullKey() {
        myHashMap.put(null, "valueNull");
        String result = myHashMap.get(null);
        assertEquals("valueNull", result);
    }

    /**
     * Тест для проверки поведения метода get() для несуществующего ключа.
     * Ожидается, что при вызове myHashMap.get("NExis") будет возвращено null.
     */

    @Test
    public void testGetNonExistingKey() {
        String result = myHashMap.get("NExis"); /// с более длинными словами не работает
        assertNull(result);
    }

}