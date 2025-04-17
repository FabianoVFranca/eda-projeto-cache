package lru;

import cacheinterface.CacheAlgorithm;

import java.util.HashMap;

/**
 * Implementação do algoritmo de cache LRU (Least Recently Used).
 * Utiliza uma combinação de HashMap e Doubly Linked List para fornecer operações eficientes.
 *
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class LRUCache<K, V> implements CacheAlgorithm<K, V> {

    private final int capacity; // capacidade do cache
    private final HashMap<K, Node<K, V>> cache; // mapa que armazena o dado (key) e o nó no qual esse dado se encontra (value)
    private final Node<K, V> head, tail;  // nós sentinela que fornecem a hierarquia de frequencia de uso dos dados armazenados no cache

    /**
     * Construtor da LRUCache.
     *
     * @param capacity Capacidade máxima do cache.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;

        // inicialização do mapa de cache
        this.cache = new HashMap<K, Node<K, V>>();

        // inicialização dos nós de início e de fim da DLL
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Adiciona um nó no final da lista (indicando uso recente).
     *
     * @param node Nó a ser adicionado.
     */
    public void addLast(Node<K, V> node) {
        node.prev = this.tail.prev;
        node.next = this.tail;
        node.prev.next = node;
        this.tail.prev = node;
    }

    /**
     * Remove um nó da lista.
     *
     * @param node Nó a ser removido.
     */
    public void removeNode(Node<K, V> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**
     * Move um nó para o final da lista, marcando como recentemente usado.
     *
     * @param node Nó a ser movido.
     */
    public void moveToTail(Node<K, V> node) {
        removeNode(node);
        addLast(node);
    }

    /**
     * Retorna o valor associado à chave, se presente no cache.
     * Atualiza a posição do item na lista para indicar uso recente.
     *
     * @param key Chave do item buscado.
     * @return Valor correspondente ou null (cache miss).
     */
    @Override
    public V get(K key) {
        if (this.cache.containsKey(key)) {
            // caso no qual houve um cache hit e temos que mover o nó para frente.
            Node<K, V> node = this.cache.get(key);
            moveToTail(node); // move o nó para moveToTail da dll
            return node.value;
        }
        // já nesse caso, houve um cache miss, pois não achamos aquela chave no cache.
        return null;
    }

    /**
     * Insere ou atualiza um item no cache.
     * Move itens existentes para o final da lista (uso recente) ou remove o menos usado se necessário.
     *
     * @param key   Chave do item.
     * @param value Valor do item.
     */
    @Override
    public void put(K key, V value) {

        if (this.cache.containsKey(key)) {
            // como o dado já se encontra no cache, temos que atualizar sua frequência
            Node<K, V> node = this.cache.get(key);
            node.value = value;  // atualiza o valor do nó
            moveToTail(node); // move o nó para o final da dll
        } else {
            // verifica se a capacidade do cache foi atingida
            if (this.cache.size() == capacity) {
                eviction();
            }
            // criamos um novo nó e adicionamos-o ao cache, já que
            // se o cache estivesse cheio, o nó LFU teria sido removido e
            // caso tenha espaço, é possível só adicionar direto
            Node<K, V> newNode = new Node<>(key, value);
            this.cache.put(key, newNode);
            addLast(newNode);
        }
    }

    /**
     * Remove o item menos recentemente usado do cache.
     */
    @Override
    public void eviction() {
        // capacidade máxima atingida e nó LRU é removido do cache para que o novo nó possa ser adicionado
        Node<K, V> leastRecentlyUsed = this.head.next;
        removeNode(leastRecentlyUsed);
        this.cache.remove(leastRecentlyUsed.key);
    }

/**
     * Exibe o estado atual do cache para fins de depuração ou visualização.
     */
    public void printCache() {
        System.out.print("head -> ");
        Node<K, V> currentNode = this.head.next;
        while (currentNode != this.tail) {
            System.out.print(currentNode.key + ":" + currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }

}
