package fifo;

import cacheinterface.CacheAlgorithm;
import java.util.Arrays;

// ta me incomodando esse generic
public class FIFOCache<T> implements CacheAlgorithm<K, V> {
    private T[] cache;       // Array para armazenar os elementos
    private int capacity;    // Capacidade máxima do cache
    private int head;        // Índice do primeiro elemento
    private int tail;        // Índice do último elemento
    private int size;        // Tamanho atual do cache

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = (T[]) new Object[capacity];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
    }
    
    private boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    
    private boolean isFull() {
        return size == capacity;
    }

    @Override
    public void put(T element) {
        if (isEmpty()) {
            
            this.head = 0;
            this.tail = 0;
        } else {
            this.tail = (this.tail + 1) % this.capacity;
        }

        this.cache[this.tail] = element;
        this.size++;
        
    }

    // @Override
    public boolean get(T element) {
        if(isEmpty()){
            put(element);
            return false;
        }

        if (isFull()) {
            eviction();
            put(element);
            return false;
            
        }
        //busca elemento na lista
        int i = this.head;
        while (true) {
            
            if (cache[i] != null && cache[i].equals(element)) return true;
            
            if (i == this.tail) break;

            i = (i + 1) % this.capacity;
        }

        put(element);
        return false;

    }


    @Override
    public T eviction() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cache vazio.");
        }

        T removedItem = this.cache[this.head];
        if (this.head == this.tail) {
            
            this.head = -1;
            this.tail = -1;
        } else {
            
            this.head = (this.head + 1) % this.capacity;
        }

        this.size--;
        return removedItem;
    }

    // @Override
    public int size() {
        return this.size;
    }

}