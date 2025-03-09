package com.my_hash_map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveTest {
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
     * Проверяет, что метод remove корректно удаляет существующий ключ и что значение для
     * этого ключа больше не доступно.
     */
    @Test
    public void testRemoveExistingKey() {
        assertTrue(myHashMap.remove("key2"));
        assertNull(myHashMap.get("key2"));
    }

    /**
     * Проверяет, что метод remove корректно удаляет значение, связанное с ключом null,
     * и что значение для этого ключа больше не доступно.
     */
    @Test
    public void testRemoveNullKey() {
        myHashMap.put(null, "valueNull");
        assertTrue(myHashMap.remove(null));
    }

    /**
     * Проверяет, что метод remove возвращает false при попытке удалить несуществующий ключ и
     * что другие ключи не затрагиваются.
     */
    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(myHashMap.remove("nEKey"));
        assertEquals("value1", myHashMap.get("key1"));
    }

    /**
     * Проверяет, что размер хэш-маппы корректно уменьшается после удаления элемента.
     */
    @Test
    public void testSizeAfterRemove() {
        assertEquals(3, myHashMap.getSize());
        myHashMap.remove("key1");
        assertEquals(2, myHashMap.getSize());
    }

    /**
     * Проверяет, что после удаления значения, связанного с существующим ключом, остались
     * доступными только существующие ключи.
     */
    @Test
    public void testRemoveUpdatesBucket() {
        myHashMap.remove("key3");

        assertEquals("value1", myHashMap.get("key1"));
        assertEquals("value2", myHashMap.get("key2"));
        assertNull(myHashMap.get("key3"));
    }
}
