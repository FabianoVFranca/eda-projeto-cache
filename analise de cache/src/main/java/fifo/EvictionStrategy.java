package fifo;


// nao sei se vai pegar em todos, a interface
public class EvictionStrategy<T> {
    private Cache<T> cache; 

    public EvictionStrategy(Cache<T> cache) {
        this.cache = cache;
    }

    
    public boolean get(T element) {
        return cache.get(element);
    }

    
    public void add(T element) {
        cache.put(element);
    }

    
    public T evict() {
        return cache.evict();
    }

    
    public int size() {
        return cache.size();
    }


}