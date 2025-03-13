package fifo;

import cacheinterface.CacheAlgorithm;

import java.util.*;

public class FIFOCache<K,V> implements CacheAlgorithm<K, V> {
    private V[] cache;       // Array para armazenar os elementos
    private int capacity;    // Capacidade máxima do cache
    private int head;        // Índice do primeiro elemento
    private int tail;        // Índice do último elemento
    private int size;        // Tamanho atual do cache
    private Map<K, V> mapSearch;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = (V[]) new Object[capacity];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
        this.mapSearch = new LinkedHashMap<>();
    }

    private boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    private boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return this.size;
    }

    @Override
    public V get(K key) {
        return mapSearch.getOrDefault(key, null);
    }

    @Override
    public void put(K key, V value) {
        if (isFull()) {
            eviction();
        }

        if (isEmpty()) {
            this.head = (this.head + 1) % this.capacity;
        }

        // Atualiza tail
        this.tail = (this.tail + 1) % this.capacity;
        this.cache[this.tail] = value;
        mapSearch.put(key, value);
        this.size++;
    }

    @Override
    public void eviction() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cache vazio.");
        }

        // Remove o item mais antigo (head)
        V removedItem = cache[this.head];

        Iterator<Map.Entry<K, V>> iterator = mapSearch.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            iterator.remove();
        }

        // Atualiza head
        this.head = (this.head + 1) % this.capacity;
        this.size--;
    }
}