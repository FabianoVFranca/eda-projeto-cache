package java.fifo;

import java.cacheinterface.CacheAlgorithm;
import java.util.LinkedHashMap;
import java.util.Map;

// ta me incomodando esse generic
public class FIFOCache<K,V> implements CacheAlgorithm<K, V> {
    private V[] cache;       // Array para armazenar os elementos
    private int capacity;    // Capacidade máxima do cache
    private int head;        // Índice do primeiro elemento
    private int tail;        // Índice do último elemento
    private int size;        // Tamanho atual do cache
    private Map <K, V> mapSearch;
 

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

    // @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(K key) {
        
        if(mapSearch.containsKey(key)) return mapSearch.get(key);
        
        return null;

    }

    @Override
    public void put(K key, V value) {
        if (isEmpty()) {
            
            this.head = 0;
            this.tail = 0;
        } else {
            this.tail = (this.tail + 1) % this.capacity;
        }

        this.cache[this.tail] = value;
        mapSearch.put(key, value);
        this.size++;
        
    }

    @Override
    public void eviction() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cache vazio.");
        }
        
        V removedItem = this.cache[this.head];
        
        // so pensei em fazer pelo mapEntry vai pegar cada entrada nodas entradas e comparar o valor, como pega o primeiro acho que da certo
        // for (Map.Entry<K, V> entry : mapSearch.entrySet()) {
        //     if (entry.getValue().equals(removedItem)) {
        //         mapSearch.remove(entry.getKey());
        //         break;
        //     }
        // }
        if(!mapSearch.isEmpty()){
            
            
            mapSearch.remove(removedItem);
        }
        
        if (this.head == this.tail) {
                    
            this.head = -1;
            this.tail = -1;
        } else {
                    
            this.head = (this.head + 1) % this.capacity;
        }

        this.size--;
            
    }

}
