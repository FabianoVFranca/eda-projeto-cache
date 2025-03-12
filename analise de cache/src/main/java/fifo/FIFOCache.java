package main.java.fifo;



// ta me incomodando esse generic
public class FIFOCache<T> {
    private T[] cache;       
    private int capacity;   
    private int head;       
    private int tail;        
    private int size;               

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

    
    public boolean put(T item) {
        
        if(this.get(item)){
            return true;
        }

        if (isFull()) {
            
            removeFirst();
        }

        if (isEmpty()) {
            
            this.head = 0;
            this.tail = 0;
        } else {
    
            this.tail = (this.tail + 1) % this.capacity;
        }

        this.cache[this.tail] = item;
        this.size++;
        return false;
    }

    
    public boolean get(T item) {
        if (isEmpty()) {
            return false;
        }

        int i = this.head;
        while (true) {
            if (cache[i] != null && cache[i].equals(item)) {
                
                return true;
            }
            if (i == this.tail) break;
            i = (i + 1) % this.capacity;
        }

        return false;
    }

    
    private T removeFirst() {
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


}