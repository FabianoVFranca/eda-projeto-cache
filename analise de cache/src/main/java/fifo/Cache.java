package fifo;

public interface Cache<T>{

    boolean get(T element);
    void put(T putted);
    T evict();
    int size();
}