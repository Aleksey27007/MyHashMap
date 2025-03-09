package com.my_hash_map;

import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MyHashMap — это реализация хэш-таблицы с использованием массивов и связанных списков для управления коллизиями.
 * Он реализует интерфейс MyMapInterface, который предоставляет основные методы для работы с парами ключ-значение,
 * такие как получение, добавление и удаление элементов.
 */
public class MyHashMap<K, V> implements MyMapInterface<K, V> {


    private int defaultLength = 16;
    /**
     * Начальный размер массива корзин хэш-таблицы.
     */

    private final int MAXIMUM_SIZE = 1000;
    /**
     * Максимально допустимый размер хэш-таблицы. При превышении этого значения будет выброшено исключение.
     */
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * Коэффициент загрузки, определяющий момент, когда следует увеличивать размер таблицы.
     */
    private final int threshold;
    /**
     * Порог, при достижении которого размер корзины будет увеличен.
     */
    private final LinkedList<Entry<K, V>>[] bucket;
    /**
     * Массив корзин, в которых будут храниться связанные списки на основе коллизий.
     */
    private int size;

    /**
     * Текущий размер хэш-таблицы, то есть количество элементов.
     */

    @ToString
    protected static class Entry<K, V> {
        public K key;
        /**
         * Ключ элемента.
         */
        public V value;

        /**
         * Значение элемента.
         */

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**
         *  Конструктор. Создаёт новую пару ключ-значение.
         */
    }

    /**
     * Вложенный класс для представления пары ключ-значение.
     */

    public MyHashMap() {
        this.bucket = new LinkedList[defaultLength];
        this.size = 0;
        this.threshold = (int) (defaultLength * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Инициализирует хэш-таблицу с заданной длиной и устанавливает начальные значения для size и threshold.
     */

    private int hash(K key) {
        return Objects.hashCode(key) % bucket.length;
    }

    /**
     * Генерирует индекс для заданного ключа, используя метод Objects.hashCode() для получения хэш-кода
     * и применяя модульную арифметику с длиной массива корзин.
     */

    @Override
    public V get(K key) {
        int index = hash(key);
        if (bucket[index] != null) {
            for (Entry<K, V> entry : bucket[index]) {
                if (entry.key.equals(key)) {
                    return bucket[index].getLast().value;
                }
            }
        }
        return null;
    }

    /**
     * Возвращает значение, связанное с заданным ключом. Если ключ не найден, возвращает null.
     */

    @Override
    public boolean put(K key, V value) {
        checkSizeForTable();
        int index = hash(key);

        if (bucket[index] == null) {
            bucket[index] = new LinkedList<>();
            bucket[index].add(new Entry<>(key, value));
        } else {
            bucket[index].add(new Entry<>(key, value));
        }
        size++;
        return true;
    }

    /**
     * Добавляет новую пару ключ-значение в хэш-таблицу. Если ключ уже существует,
     * новая пара будет добавлена в соответствующую корзину. В случае заполнения таблицы будет также
     * вызвана проверка на её увеличение.
     */

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

    /**
     * Удаляет пару ключ-значение из хэш-таблицы по заданному ключу. Если такой ключ не найден, ничего не происходит.
     */

    @Override
    public Collection<V> values() {
        return Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(entry -> entry.value)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает коллекцию всех значений, хранящихся в хэш-таблице.
     */

    @Override
    public Set<K> keySet() {
        return Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(entry -> entry.key)
                .collect(Collectors.toSet());
    }

    /**
     * Возвращает набор всех ключей, хранящихся в хэш-таблице.
     */

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Возвращает набор всех пар ключ-значение, хранящихся в хэш-таблице.
     */

    private void checkSizeForTable() {
        if (size >= MAXIMUM_SIZE) {
            throw new IndexOutOfBoundsException("checkSizeForTable() - out of bounds length.");
        }

        if (size == threshold) {
            defaultLength *= 2;
        }
    }

    /**
     * Проверяет, достигнут ли предел размерности хэш-таблицы, и увеличивает её во два раза,
     * если порог превышен. Выбрасывает исключение IndexOutOfBoundsException при превышении максимального размера.
     */

    public int getSize() {
        return size;
    }
    /**
     *  Возвращает текущее количество элементов в хэш-таблице.
     */
}
