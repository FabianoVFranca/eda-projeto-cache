package fifo;

import cacheinterface.CacheAlgorithm;
import java.util.*;

/**
 * Implementação do algoritmo de cache FIFO (First-In-First-Out).
 * Elementos mais antigos são removidos primeiro quando o cache atinge sua capacidade.
 * 
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class FIFOCache<K,V> implements CacheAlgorithm<K, V> {
    private V[] cache;       // Array para armazenar os elementos
    private int capacity;    // Capacidade máxima do cache
    private int head;        // Índice do primeiro elemento
    private int tail;        // Índice do último elemento
    private int size;        // Tamanho atual do cache
    private Map<K, V> mapSearch;

    /**
     * Construtor da classe FIFOCache.
     *
     * @param capacity Capacidade máxima do cache.
     */
    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = (V[]) new Object[capacity];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
        this.mapSearch = new LinkedHashMap<>();
    }

    /**
     * Verifica se o cache está vazio.
     *
     * @return true se estiver vazio, false caso contrário.
     */
    private boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    /**
     * Verifica se o cache está cheio.
     *
     * @return true se estiver cheio, false caso contrário.
     */
    private boolean isFull() {
        return size == capacity;
    }

    /**
     * Retorna o número de elementos atualmente armazenados no cache.
     *
     * @return tamanho atual do cache.
     */
    public int size() {
        return this.size;
    }

    /**
     * Recupera um valor do cache com base na chave.
     *
     * @param key Chave do item a ser recuperado.
     * @return Valor associado à chave ou null se não estiver presente.
     */
    @Override
    public V get(K key) {
        return mapSearch.getOrDefault(key, null);
    }

    /**
     * Insere um novo item no cache. Caso o cache esteja cheio,
     * o item mais antigo será removido.
     *
     * @param key   Chave do item.
     * @param value Valor do item.
     */
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

    /**
     * Remove o item mais antigo do cache (seguindo a política FIFO).
     * Lança exceção se o cache estiver vazio.
     */
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