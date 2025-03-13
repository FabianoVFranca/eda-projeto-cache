package fifo;

import java.util.LinkedHashMap;
import java.util.Map;

import java.util.LinkedHashMap;
import java.util.Map;

import cacheinterface.CacheAlgorithm;

public class FIFOCache<K, V> implements CacheAlgorithm<K, V> {
    private V[] cache;       // Array para armazenar os elementos
    private int capacity;    // Capacidade máxima do cache
    private int head;        // Índice do primeiro elemento
    private int tail;        // Índice do último elemento
    private int size;        // Tamanho atual do cache
    private Map<K, V> mapSearch;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = (V[]) new Object[capacity];
        this.head = 0;  // Inicializa head como 0
        this.tail = -1; // Inicializa tail como -1 (cache vazio)
        this.size = 0;
        this.mapSearch = new LinkedHashMap<>();
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.size == capacity;
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
        V removedItem = cache[head];
        mapSearch.values().remove(removedItem);

        // Atualiza head
        this.head = (this.head + 1) % this.capacity;
        this.size--;
    }
}