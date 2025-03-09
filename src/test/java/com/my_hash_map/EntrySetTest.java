package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.my_hash_map.MyHashMap.Entry;

import java.util.AbstractMap;
import java.util.Set;

public class EntrySetTest {
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
     * Проверяет, что метод entrySet возвращает все пары ключ-значение, находящиеся в хэш-маппе.
     */
    @Test
    public void testEntrySetReturnsAllEntries() {
        Set<Entry<String, String>> entries = myHashMap.entrySet();
        assertEquals(3, entries.size());
    }

    /**
     * Проверяет, что метод работаеткорректно после удаления ключа.
     */
    @Test
    public void testEntrySetAfterRemove() {
        myHashMap.remove("key2");
        Set<Entry<String, String>> entries = myHashMap.entrySet();
        assertEquals(2, entries.size());
    }

    /**
     * Проверяет, что метод entrySet возвращает пустой набор, когда хэш-маппа пуста.
     */
    @Test
    public void testEntrySetWhenEmpty() {
        MyHashMap<String, String> emptyMap = new MyHashMap<>();
        Set<Entry<String, String>> entries = emptyMap.entrySet();
        assertTrue(entries.isEmpty());
    }

    /**
     * Проверяет, что пары с ключами и значениями типа null корректно добавляются.
     */
    @Test
    public void testEntrySetWithNullKey() {
        myHashMap.put(null, "nullValue");
        Set<Entry<String, String>> entries = myHashMap.entrySet();

        assertEquals(4, entries.size());
    }
}
