package java.cacheinterface;

public interface CacheAlgorithm<K, V> {
    
    public V get(K key);

    public void put(K key, V value);

    public void eviction();
    
}