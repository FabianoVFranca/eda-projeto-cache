import cacheinterface.CacheAlgorithm;

/**
 * Classe que representa uma estratégia genérica de gerenciamento de cache.
 * Encapsula qualquer algoritmo de substituição de cache que implemente a interface {@code CacheAlgorithm}.
 *
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class CacheEvictionStrategy<K, V> {
    
    private CacheAlgorithm<K, V> cache;

    /**
     * Construtor da estratégia de gerenciamento de cache.
     *
     * @param cache Instância de um algoritmo de cache que será utilizado (FIFO, LRU ou LFU).
     */
    public CacheEvictionStrategy(CacheAlgorithm<K, V> cache) {
        this.cache = cache;
    }
    
    /**
     * Verifica se a chave está presente no cache.
     *
     * @param key Chave a ser buscada.
     * @return {@code true} se a chave está presente (cache hit), {@code false} caso contrário (cache miss).
     */
    public boolean get(K key) {
        if (cache.get(key) != null) return true;
        return false;
    }

    /**
     * Insere um item no cache, utilizando a política de substituição definida pela implementação.
     *
     * @param key   Chave do item.
     * @param value Valor do item.
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }
    
}