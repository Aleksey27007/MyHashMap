package com.my_hash_map;

import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

public class MyHashMap<K, V> implements MyMapInterface<K, V> {


    /**
     * Начальный размер массива корзин хэш-таблицы.
     */
    private int defaultLength = 16;


    /**
     * Максимально допустимый размер хэш-таблицы. При превышении этого значения будет выброшено исключение.
     */
    private final int MAXIMUM_SIZE = 1000;

    /**
     * Коэффициент загрузки, определяющий момент, когда следует увеличивать размер таблицы.
     */
    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Порог, при достижении которого размер корзины будет увеличен.
     */
    private final int threshold;

    /**
     * Массив корзин, в которых будут храниться связанные списки.
     */
    private final LinkedList<Entry<K, V>>[] bucket;

    /**
     * Текущий размер хэш-таблицы, то есть количество элементов.
     */
    private int size;


    /**
     * Вложенный класс для представления пары ключ-значение.
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
     * Инициализирует хэш-таблицу с заданной длиной и устанавливает начальные значения для size и threshold.
     */
    public MyHashMap() {
        this.bucket = new LinkedList[defaultLength];
        this.size = 0;
        this.threshold = (int) (defaultLength * DEFAULT_LOAD_FACTOR);
    }


    /**
     * Генерирует индекс для заданного ключа, используя метод Objects.hashCode() для получения хэш-кода
     * и применяя модульную арифметику с длиной массива корзин.
     */
    private int hash(K key) {
        return Objects.hashCode(key) % bucket.length;
    }


    /**
     * Возвращает значение, связанное с заданным ключом. Если ключ не найден, возвращает null.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        if (key == null) {
            return bucket[index].getLast().value;
        } else if (bucket[index] != null) {
            for (Entry<K, V> entry : bucket[index]) {
                if (entry.key.equals(key)) {
                    return bucket[index].getLast().value;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * Добавляет новую пару ключ-значение в хэш-таблицу. Если ключ уже существует,
     * новая пара будет добавлена в соответствующую корзину. В случае заполнения таблицы будет также
     * вызвана проверка на её увеличение.
     */
    @Override
    public boolean put(K key, V value) {
        checkSizeForTable();
        int index = hash(key);
        if (key == null) {
            if (bucket[0] == null) {
                bucket[index] = new LinkedList<>();
                bucket[index].add(new Entry<>(null, value));
                size++;
            } else {
                bucket[0].add(new Entry<>(null, value));
            }
        } else {
            if (bucket[index] == null) {
                bucket[index] = new LinkedList<>();
                bucket[index].add(new Entry<>(key, value));
                size++;
            } else {
                bucket[index].add(new Entry<>(key, value));
            }
        }
        return true;
    }

    /**
     * Удаляет пару ключ-значение из хэш-таблицы по заданному ключу. Если такой ключ не найден, ничего не происходит.
     */

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        if (key == null) {
            bucket[0].remove();
            size--;
            return true;
        } else if (bucket[index] != null) {
            bucket[index].remove();
            size--;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Возвращает коллекцию всех значений, хранящихся в хэш-таблице.
     */

    @Override
    public Collection<V> values() {
        return Arrays.stream(bucket).filter(Objects::nonNull).flatMap(Collection::stream).map(entry -> entry.value).collect(Collectors.toList());
    }

    /**
     * Возвращает набор всех ключей, хранящихся в хэш-таблице.
     */

    @Override
    public Set<K> keySet() {
        return Arrays.stream(bucket).filter(Objects::nonNull).flatMap(Collection::stream).map(entry -> entry.key).collect(Collectors.toSet());
    }

    /**
     * Возвращает набор всех пар ключ-значение, хранящихся в хэш-таблице.
     */

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Arrays.stream(bucket).filter(Objects::nonNull).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * Проверяет, достигнут ли предел размерности хэш-таблицы, и увеличивает её в два раза,
     * если порог превышен. Выбрасывает исключение IndexOutOfBoundsException при превышении максимального размера.
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
     * Проверяет наличие ключа в хэш-таблице
     */
    public boolean containsKey(K key) {
        return get(key) != null;
     }

    /**
     * Возвращает текущее количество элементов в хэш-таблице.
     */

    public int getSize() {
        return size;
    }

}
