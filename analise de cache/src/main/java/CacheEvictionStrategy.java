package java;

import java.cacheinterface.CacheAlgorithm;

public class CacheEvictionStrategy<K, V> {
    
    private CacheAlgorithm<K, V> cache;

    public CacheEvictionStrategy(CacheAlgorithm<K, V> cache) {
        this.cache = cache;
    }
    
    public boolean get(K key) {
        if (cache.get(key) != null) return true;
        return false;
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }
    
}
