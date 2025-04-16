package lru;

/**
 * Representa um nó da lista duplamente encadeada usada no LRUCache.
 * Armazena chave, valor e referências para os nós anterior e posterior.
 *
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class Node<K, V> {
    K key;  // identificador do dado armazenado
    V value;  // dado armazenado

    Node<K, V> prev;  // informação sobre o nó anterior
    Node<K, V> next;  // informação sobre o próximo nó

    /**
     * Construtor do nó.
     *
     * @param key   Chave do item.
     * @param value Valor do item.
     */
    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }
}